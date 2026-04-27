package com.georescue.victim.data

import android.annotation.SuppressLint
import android.util.Log
import com.georescue.victim.data.repository.SignalRepository
import com.georescue.victim.domain.models.SignalType
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Streams real device telemetry (location + battery) to RTDB `locations/{userId}`
 * every [STREAM_INTERVAL_MS] milliseconds while the [DetectionService] is running.
 *
 * RTDB schema (matches architecture.md §3):
 * {
 *   "lat":       number,
 *   "lng":       number,
 *   "battery":   number,
 *   "updatedAt": ServerValue.TIMESTAMP
 * }
 */
@Singleton
class LiveStatusStreamer @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase,
    private val fusedLocation: FusedLocationProviderClient,
    private val batteryReader: BatteryReader
) {

    companion object {
        private const val STREAM_INTERVAL_MS = 15_000L   // 15 seconds
        private const val LOCATION_INTERVAL_MS = 10_000L // Location update request interval
    }

    /**
     * Starts the real-time telemetry loop.
     * Suspends until the calling coroutine is cancelled (i.e., service destroyed).
     */

    //for testing
//    @Inject
//    lateinit var signalRepository: SignalRepository

    suspend fun startStreaming() {
        val userId = auth.currentUser?.uid ?: run {
            Log.w("LiveStatusStreamer", "Cannot stream — user not authenticated")
            return
        }

        val locationsRef = database.getReference("locations").child(userId)
        Log.d("LiveStatusStreamer", "Starting telemetry stream for userId=$userId")

        //for testing
        //signalRepository.sendSignal(SignalType.INACTIVITY)

        getLocationFlow().collect { (lat, lng) ->
            val battery = batteryReader.getBatteryLevel()
            Log.d("LiveStatusStreamer", "Telemetry → lat=$lat lng=$lng battery=$battery%")
            val update = mapOf(
                "lat"       to lat,
                "lng"       to lng,
                "battery"   to battery,
                "updatedAt" to ServerValue.TIMESTAMP
            )
            locationsRef.setValue(update)
                .addOnSuccessListener {
                    Log.d("LiveStatusStreamer", "Telemetry → lat=$lat lng=$lng battery=$battery%")
                }
                .addOnFailureListener { e ->
                    Log.e("LiveStatusStreamer", "RTDB write failed: ${e.message}")
                }

            // Throttle writes to STREAM_INTERVAL_MS
            delay(STREAM_INTERVAL_MS)
        }
    }

    /**
     * Produces a continuous [Flow] of (lat, lng) pairs from [FusedLocationProviderClient].
     * Uses [callbackFlow] to bridge the GMS callback API into coroutines.
     */
    @SuppressLint("MissingPermission")
    private fun getLocationFlow(): Flow<Pair<Double, Double>> = callbackFlow {
        val request = LocationRequest.Builder(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            LOCATION_INTERVAL_MS
        ).build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation ?: return
                trySend(Pair(loc.latitude, loc.longitude))
            }
        }

        fusedLocation.requestLocationUpdates(request, callback, android.os.Looper.getMainLooper())

        awaitClose {
            fusedLocation.removeLocationUpdates(callback)
            Log.d("LiveStatusStreamer", "Location updates removed")
        }
    }
}
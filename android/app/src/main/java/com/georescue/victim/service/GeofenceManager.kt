package com.georescue.victim.service

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.georescue.victim.domain.models.RiskZone
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeofenceManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val geofencingClient: GeofencingClient
) {
    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(context, GeofenceBroadcastReceiver::class.java)
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        PendingIntent.getBroadcast(context, 0, intent, flags)
    }

    @SuppressLint("MissingPermission")
    fun addGeofences(riskZones: List<RiskZone>) {
        if (riskZones.isEmpty()) {
            Log.d("GEOFENCE_MANAGER", "No risk zones to add")
            return
        }

        val geofences = riskZones.map { zone ->
            Geofence.Builder()
                .setRequestId(zone.zoneId)
                .setCircularRegion(
                    zone.center.latitude,
                    zone.center.longitude,
                    zone.radius.toFloat()
                )
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
                .build()
        }

        Log.d("GEOFENCE_MANAGER", "Attempting to register ${geofences.size} geofences")

        val request = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofences(geofences)
            .build()

        geofencingClient.addGeofences(request, geofencePendingIntent).run {
            addOnSuccessListener {
                Log.d("GEOFENCE_MANAGER", "Geofences registered successfully")
            }
            addOnFailureListener { e ->
                Log.e("GEOFENCE_MANAGER", "Geofence registration failed: ${e.message}", e)
            }
        }
    }

    fun removeGeofences() {
        geofencingClient.removeGeofences(geofencePendingIntent).run {
            addOnSuccessListener {
                Log.d("GEOFENCE_MANAGER", "Geofences removed successfully")
            }
            addOnFailureListener { e ->
                Log.e("GEOFENCE_MANAGER", "Failed to remove geofences: ${e.message}", e)
            }
        }
    }
}

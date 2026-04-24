package com.georescue.victim.data

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LiveStatusStreamer @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: FirebaseDatabase
) {

    /**
     * Starts streaming location to RTDB.
     * Includes both a mocked approach and an injection approach.
     * The unused one can be commented out by the user.
     */
    suspend fun startStreaming() {
        val userId = auth.currentUser?.uid ?: return
        val locationsRef = database.getReference("locations").child(userId)

        // METHOD 1: Mocked Coordinates (Uncomment to use)
        /*
        var lat = 37.7749
        var lng = -122.4194
        while (true) {
            val update = mapOf(
                "lat" to lat,
                "lng" to lng,
                "battery" to 85, // Mock battery
                "updatedAt" to ServerValue.TIMESTAMP
            )
            locationsRef.setValue(update)
            
            // Slightly move coordinates for testing
            lat += 0.0001
            lng += 0.0001
            
            delay(10000L) // 10 seconds
        }
        */

        // METHOD 2: Using a generic flow of coordinates (Uncomment and inject real flow to use)
        // Assuming we have a Flow of location updates from FusedLocationProviderClient
        
        getMockLocationFlow().collect { location ->
            val update = mapOf(
                "lat" to location.first,
                "lng" to location.second,
                "battery" to 90, // Real battery level should be fetched here
                "updatedAt" to ServerValue.TIMESTAMP
            )
            locationsRef.setValue(update)
        }
        
    }

    // Temporary mock flow to satisfy METHOD 2 compilation.
    // Replace this with actual location provider flow.
    private fun getMockLocationFlow(): Flow<Pair<Double, Double>> = flow {
        var lat = 37.7749
        var lng = -122.4194
        while (true) {
            emit(Pair(lat, lng))
            lat += 0.0001
            lng += 0.0001
            delay(10000L) // 10 seconds
        }
    }
}

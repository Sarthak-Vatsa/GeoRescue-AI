package com.georescue.victim.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val event = GeofencingEvent.fromIntent(intent) ?: return
        if (event.hasError()) {
            Log.e("GeofenceReceiver", "Geofencing error: ${event.errorCode}")
            return
        }

        val transition = event.geofenceTransition
        when (transition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                Log.d("GeofenceReceiver", "Entered Geofence")
                startDetectionService(context)
            }
            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                Log.d("GeofenceReceiver", "Exited Geofence")
                stopDetectionService(context)
            }
        }
    }

    private fun startDetectionService(context: Context) {
        val sharedPrefs = context.getSharedPreferences("GeoRescuePrefs", Context.MODE_PRIVATE)
        val uid = sharedPrefs.getString("USER_UID", null)

        if (uid == null) {
            Log.e("GeofenceReceiver", "UID not found. Skipping service start.")
            return
        }

        val intent = Intent(context, DetectionService::class.java).apply {
            action = DetectionService.ACTION_START
        }
        context.startForegroundService(intent)
    }

    private fun stopDetectionService(context: Context) {
        val intent = Intent(context, DetectionService::class.java).apply {
            action = DetectionService.ACTION_STOP
        }
        context.startService(intent)
    }
}

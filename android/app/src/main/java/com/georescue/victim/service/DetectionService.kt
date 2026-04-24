package com.georescue.victim.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.georescue.victim.R
import dagger.hilt.android.AndroidEntryPoint

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.georescue.victim.data.LiveStatusStreamer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.cancel
import javax.inject.Inject

@AndroidEntryPoint
class DetectionService : Service() {

    @Inject
    lateinit var auth: FirebaseAuth

    @Inject
    lateinit var database: FirebaseDatabase

    @Inject
    lateinit var liveStatusStreamer: LiveStatusStreamer

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startForegroundService()
            ACTION_STOP -> stopSelf()
        }
        return START_STICKY
    }

    private fun startForegroundService() {
        createNotificationChannel()
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("GeoRescue AI Active")
            .setContentText("Monitoring for risk in your area...")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .build()

        startForeground(NOTIFICATION_ID, notification)
        
        setupRTDBPresence()
        startTelemetryStream()
    }

    private fun setupRTDBPresence() {
        val userId = auth.currentUser?.uid ?: return
        val presenceRef = database.getReference("presence").child(userId)

        // Set up the onDisconnect hook
        val disconnectData = mapOf(
            "isOnline" to false,
            "lastSeen" to ServerValue.TIMESTAMP
        )
        presenceRef.onDisconnect().setValue(disconnectData)

        // Set user to online
        val onlineData = mapOf(
            "isOnline" to true,
            "lastSeen" to ServerValue.TIMESTAMP
        )
        presenceRef.setValue(onlineData)
    }

    private fun startTelemetryStream() {
        serviceScope.launch {
            liveStatusStreamer.startStreaming()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val presenceRef = database.getReference("presence").child(userId)
            val offlineData = mapOf(
                "isOnline" to false,
                "lastSeen" to ServerValue.TIMESTAMP
            )
            presenceRef.setValue(offlineData)
        }
        serviceScope.cancel()
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Risk Detection Channel",
            NotificationManager.IMPORTANCE_LOW
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        private const val CHANNEL_ID = "risk_detection_channel"
        private const val NOTIFICATION_ID = 1001
    }
}

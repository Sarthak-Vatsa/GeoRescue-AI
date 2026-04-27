package com.georescue.victim.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.georescue.victim.R
import dagger.hilt.android.AndroidEntryPoint

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.georescue.victim.data.LiveStatusStreamer
import com.georescue.victim.data.repository.IncidentObserver
import com.georescue.victim.domain.usecases.FailsafeTimer
import com.georescue.victim.domain.usecases.InactivityUseCase
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

    @Inject
    lateinit var inactivityUseCase: InactivityUseCase

    @Inject
    lateinit var failsafeTimer: FailsafeTimer

    @Inject
    lateinit var incidentObserver: IncidentObserver

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

        //Log.d("DETECTION_SERVICE", "Starting foreground service")
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("GeoRescue AI Active")
            .setContentText("Monitoring for risk in your area...")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        //Log.d("DETECTION_SERVICE", "Foreground service started")

        setupRTDBPresence()
        incidentObserver.startObserving()
        startDetectionPipelines()
    }

    private fun setupRTDBPresence() {
        // 1. Try to get the instant cached UID first (Step 2 integration)
        val sharedPrefs = getSharedPreferences("GeoRescuePrefs", Context.MODE_PRIVATE)
        val cachedUid = sharedPrefs.getString("USER_UID", null)

        if (cachedUid != null) {
            // SUCCESS: We have the UID instantly, bypass Firebase Auth cold-start!
            //Log.d("DETECTION_SERVICE", "Found cached UID: $cachedUid. Writing to RTDB instantly.")
            writePresence(cachedUid)
        } else {
            // FALLBACK: No cache found. Fall back to your original Auth listener logic.
            //Log.w("DETECTION_SERVICE", "No cached UID found. Waiting for Auth state...")

            val authStateListener = object : FirebaseAuth.AuthStateListener {
                override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
                    val resolvedUser = firebaseAuth.currentUser
                    if (resolvedUser != null) {
                        //Log.d("DETECTION_SERVICE", "Auth resolved via listener! UID: ${resolvedUser.uid}")
                        writePresence(resolvedUser.uid)
                        auth.removeAuthStateListener(this) // Prevent memory leaks
                    }
                }
            }
            auth.addAuthStateListener(authStateListener)
        }
    }

    private fun writePresence(userId: String) {
        //Log.d("DETECTION_SERVICE", "Setting presence for UID: $userId")
        val presenceRef = database.getReference("presence").child(userId)

        // Set up the onDisconnect hook
        val disconnectData = mapOf(
            "isOnline" to false,
            "lastSeen" to ServerValue.TIMESTAMP
        )
        presenceRef.onDisconnect().setValue(disconnectData).addOnCompleteListener {
//            if (it.isSuccessful) {
//                Log.d("DETECTION_SERVICE", "onDisconnect hook set successfully")
//            } else {
//                Log.e("DETECTION_SERVICE", "Failed to set onDisconnect: ${it.exception?.message}")
//            }
        }

        // Set user to online
        val onlineData = mapOf(
            "isOnline" to true,
            "lastSeen" to ServerValue.TIMESTAMP
        )
        presenceRef.setValue(onlineData).addOnCompleteListener {
//            if (it.isSuccessful) {
//                Log.d("DETECTION_SERVICE", "User set to online")
//            } else {
//                Log.e("DETECTION_SERVICE", "Failed to set user to online: ${it.exception?.message}")
//            }
        }
    }

    private fun startDetectionPipelines() {

        Log.d("DETECTION_SERVICE", "Starting telemetry + inactivity monitoring")

        serviceScope.launch {
            liveStatusStreamer.startStreaming()
        }

        // 🔹 Stream 2 (sensor + inactivity)
        serviceScope.launch {
            inactivityUseCase.monitorInactivity().collect { isInactive ->

                Log.d("INACTIVITY", "Inactive: $isInactive")

                if (isInactive) {
                    Log.d("TIMER", "Starting timer")
                    failsafeTimer.startTimer(serviceScope)
                } else {
                    Log.d("TIMER", "Resetting timer")
                    failsafeTimer.resetTimer()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //Log.d("DETECTION_SERVICE", "Service onDestroy called")
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val presenceRef = database.getReference("presence").child(userId)
            val offlineData = mapOf(
                "isOnline" to false,
                "lastSeen" to ServerValue.TIMESTAMP
            )
            presenceRef.setValue(offlineData)
        }
        incidentObserver.stopObserving()
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
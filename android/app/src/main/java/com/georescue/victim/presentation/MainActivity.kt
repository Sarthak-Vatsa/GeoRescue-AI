package com.georescue.victim.presentation

import android.Manifest
import android.R.attr.action
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.georescue.victim.data.repository.AuthRepository
import com.georescue.victim.data.repository.RiskZoneRepository
import com.georescue.victim.service.DetectionService
import com.georescue.victim.service.GeofenceManager
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var authRepository: AuthRepository
    @Inject lateinit var riskZoneRepository: RiskZoneRepository
    @Inject lateinit var geofenceManager: GeofenceManager

    private var isAuthenticated = false
    private var hasLocationPermission = false

    private var showBackgroundRationaleDialog by mutableStateOf(false)

    private val backgroundPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            hasLocationPermission = true
            maybeStartMonitoring()
        }
    }

    private val foregroundPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false
        val notificationsGranted = permissions[Manifest.permission.POST_NOTIFICATIONS] ?: true // Default true for older APIs

        if (fineLocationGranted || coarseLocationGranted) {
            // Log notification status for debugging
            hasLocationPermission = true
            if (!notificationsGranted) {
                Log.w("MainActivity", "Notification permission denied - Service might be silent")
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                showBackgroundRationaleDialog = true
            } else {
                maybeStartMonitoring()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "GeoRescue AI Initialized")
            }

            if (showBackgroundRationaleDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showBackgroundRationaleDialog = false
                    },
                    title = {
                        Text(text = "Background Location Required")
                    },
                    text = {
                        Text(text = "GeoRescue needs background location access to detect when you enter a Risk Zone even when the app is closed.")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showBackgroundRationaleDialog = false
                                backgroundPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            }
                        ) {
                            Text("Grant")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showBackgroundRationaleDialog = false
                            }
                        ) {
                            Text("No Thanks")
                        }
                    }
                )
            }
        }

        // Inside onCreate, replace your existing launcher call:
        val permissionsToRequest = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }.toTypedArray()

        foregroundPermissionLauncher.launch(permissionsToRequest)

        signInAndStartMonitoring()
    }

    private fun signInAndStartMonitoring() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = authRepository.signInAnonymously()

            if (result.isSuccess) {
                isAuthenticated = true
                Log.d("MainActivity", "Auth success, starting monitoring")
                maybeStartMonitoring()
            } else {
                Log.e(
                    "MainActivity",
                    "Auth failed: ${result.exceptionOrNull()?.message}",
                    result.exceptionOrNull()
                )
            }
        }
    }

    private fun maybeStartMonitoring() {
        if (isAuthenticated && hasLocationPermission) {
            Log.d("MainActivity", "All conditions met → starting monitoring")
            startMonitoring()
        } else {
            Log.d(
                "MainActivity",
                "Waiting → auth=$isAuthenticated, permission=$hasLocationPermission"
            )
        }
    }

    private fun startMonitoring() {
        CoroutineScope(Dispatchers.Main).launch {
            riskZoneRepository.getRiskZones().collect { zones ->
                geofenceManager.addGeofences(zones)
            }
        }
    }
}

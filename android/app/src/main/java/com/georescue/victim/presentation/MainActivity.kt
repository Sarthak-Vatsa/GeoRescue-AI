package com.georescue.victim.presentation

import android.Manifest
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

    private var showBackgroundRationaleDialog by mutableStateOf(false)

    private val backgroundPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startMonitoring()
        }
    }

    private val foregroundPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

        if (fineLocationGranted || coarseLocationGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                showBackgroundRationaleDialog = true
            } else {
                startMonitoring()
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

        foregroundPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        
        signIn()
    }

    private fun signIn() {
        CoroutineScope(Dispatchers.Main).launch {
            authRepository.signInAnonymously()
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

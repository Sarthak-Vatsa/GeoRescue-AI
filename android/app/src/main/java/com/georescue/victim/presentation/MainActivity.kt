package com.georescue.victim.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.georescue.victim.data.repository.AuthRepository
import com.georescue.victim.data.repository.RiskZoneRepository
import com.georescue.victim.presentation.theme.GeoRescueTheme
import com.georescue.victim.service.GeofenceManager
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

    private val viewModel: IncidentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Kick off anonymous auth in the background on startup
        signInAndStartMonitoring()

        setContent {
            GeoRescueTheme {
                GeoRescueNavGraph(
                    viewModel = viewModel,
                    onStartMonitoring = {
                        // The user granted permissions in the PermissionScreen
                        startMonitoring()
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // If the user opens the app while an incident is active, make sure we are observing it
        viewModel.ensureObserving()
    }

    private fun signInAndStartMonitoring() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = authRepository.signInAnonymously()
            if (result.isSuccess) {
                Log.d("MainActivity", "Auth success")
            } else {
                Log.e("MainActivity", "Auth failed: ${result.exceptionOrNull()?.message}", result.exceptionOrNull())
            }
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

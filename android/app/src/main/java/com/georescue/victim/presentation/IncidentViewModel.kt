package com.georescue.victim.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georescue.victim.data.repository.IncidentObserver
import com.georescue.victim.data.repository.RiskZoneRepository
import com.georescue.victim.domain.models.Incident
import com.georescue.victim.domain.models.IncidentStatus
import com.georescue.victim.domain.models.RiskZone
import com.georescue.victim.domain.usecases.FailsafeTimer
import com.georescue.victim.domain.usecases.SignalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng

/**
 * Shared ViewModel for the entire GeoRescue victim UI.
 *
 * Surfaces:
 *  - [currentIncident] — live Firestore incident for this user (read-only observer)
 *  - [riskZones] — loaded once for map overlays
 *  - [failsafeRemainingTime] — countdown seconds, drives the animated ring
 *  - [uiState] — derived state that drives NavGraph navigation transitions
 *
 * Action entry points for the UI:
 *  - [triggerSOSManually] — called by the "SEND SOS NOW" button
 *  - [cancelFailsafe] — called by the "I'M SAFE" button
 *  - [ensureObserving] — called when app foregrounds without DetectionService
 */
@HiltViewModel
class IncidentViewModel @Inject constructor(
    private val incidentObserver: IncidentObserver,
    private val signalUseCase: SignalUseCase,
    private val failsafeTimer: FailsafeTimer,
    private val riskZoneRepository: RiskZoneRepository,
    private val fusedLocationClient: FusedLocationProviderClient
) : ViewModel() {

    // ─── User Location ───────────────────────────────────────────────────────

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation.asStateFlow()

    @SuppressLint("MissingPermission")
    fun fetchLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                _userLocation.value = LatLng(location.latitude, location.longitude)
            }
        }
    }

    // ─── Incident ────────────────────────────────────────────────────────────

    /** Latest active incident for this user. Null when none exists. */
    val currentIncident: StateFlow<Incident?> = incidentObserver.currentIncident

    // ─── Failsafe Timer ──────────────────────────────────────────────────────

    /** Countdown in seconds (0…30). Drives the circular progress ring. */
    val failsafeRemainingTime: StateFlow<Int> = failsafeTimer.remainingTime

    /** True while the countdown is actively ticking. */
    val isFailsafeActive: StateFlow<Boolean> = failsafeTimer.isActive

    // ─── Risk Zones (for Map tab) ────────────────────────────────────────────

    private val _riskZones = MutableStateFlow<List<RiskZone>>(emptyList())
    val riskZones: StateFlow<List<RiskZone>> = _riskZones.asStateFlow()

    init {
        loadRiskZones()
    }

    private fun loadRiskZones() {
        viewModelScope.launch {
            riskZoneRepository.getRiskZones().collect { zones ->
                _riskZones.value = zones
            }
        }
    }

    // ─── Derived UI State (drives NavGraph navigation) ────────────────────────

    /**
     * Priority order:
     *  1. Active non-resolved incident → RescueActive
     *  2. Failsafe timer running       → SOSCountdown
     *  3. Otherwise                    → Monitoring
     */
    val uiState: StateFlow<GeoRescueUiState> = combine(
        incidentObserver.currentIncident,
        failsafeTimer.isActive
    ) { incident, timerActive ->
        when {
            incident != null && incident.status != IncidentStatus.RESOLVED ->
                GeoRescueUiState.RescueActive(incident)
            timerActive ->
                GeoRescueUiState.SOSCountdown
            else ->
                GeoRescueUiState.Monitoring
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, GeoRescueUiState.Monitoring)

    // ─── Actions ─────────────────────────────────────────────────────────────

    /** Starts Firestore incident listener if not already running. */
    fun ensureObserving() {
        incidentObserver.startObserving()
    }

    /** Called by "SEND SOS NOW" button on SOSCountdownScreen. */
    fun triggerSOSManually() {
        viewModelScope.launch {
            signalUseCase.triggerSOS()
        }
    }

    /** Called by "I'M SAFE" button on SOSCountdownScreen. */
    fun cancelFailsafe() {
        failsafeTimer.stopTimer()
    }

    override fun onCleared() {
        super.onCleared()
        // Do NOT stop observing — DetectionService may still be running.
    }
}

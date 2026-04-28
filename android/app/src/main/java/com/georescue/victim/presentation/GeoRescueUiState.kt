package com.georescue.victim.presentation

import com.georescue.victim.domain.models.Incident

/**
 * Sealed state class that drives navigation across the entire app.
 *
 * Derived in [IncidentViewModel] from the combination of:
 *  - [com.georescue.victim.data.repository.IncidentObserver.currentIncident]
 *  - [com.georescue.victim.domain.usecases.FailsafeTimer.isActive]
 *
 * Observed via LaunchedEffect in [GeoRescueNavGraph] to trigger navigation transitions.
 */
sealed class GeoRescueUiState {
    /** Default state — sensors are active, user is safe, monitoring in progress. */
    object Monitoring : GeoRescueUiState()

    /**
     * Inactivity detected — the 30-second failsafe countdown is running.
     * UI shows the SOS Countdown screen with the animated ring.
     */
    object SOSCountdown : GeoRescueUiState()

    /**
     * A signal has been sent and an incident exists for this user.
     * UI shows the Rescue Tracking screen with a live timeline.
     */
    data class RescueActive(val incident: Incident) : GeoRescueUiState()
}

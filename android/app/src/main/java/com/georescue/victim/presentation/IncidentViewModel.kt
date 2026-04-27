package com.georescue.victim.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georescue.victim.data.repository.IncidentObserver
import com.georescue.victim.domain.models.Incident
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * ViewModel that surfaces the current incident state to the UI layer.
 *
 * Wraps [IncidentObserver] (a Singleton) so the UI reacts to real-time
 * Firestore updates whenever the incident status changes:
 *   CREATED → ASSIGNED → RESPONDING → RESOLVED
 *
 * The observer is started/stopped by [DetectionService] for background safety.
 * This ViewModel only reads the shared [StateFlow] — it does not own the listener lifecycle.
 */
@HiltViewModel
class IncidentViewModel @Inject constructor(
    private val incidentObserver: IncidentObserver
) : ViewModel() {

    /**
     * Emits the latest incident for the current user, or null if none exists.
     * Compose screens collect this to drive the RescueStatusCard and Safety Banner.
     */
    val currentIncident: StateFlow<Incident?> = incidentObserver.currentIncident

    /**
     * Called when the app is in the foreground without DetectionService running
     * (e.g., user opens the app manually). Starts observing if not already started.
     */
    fun ensureObserving() {
        incidentObserver.startObserving()
    }

    override fun onCleared() {
        super.onCleared()
        // Do NOT stop observing here — DetectionService may still be running.
        // Observation is stopped by DetectionService.onDestroy() instead.
    }
}

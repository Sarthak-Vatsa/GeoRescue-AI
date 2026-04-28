package com.georescue.victim.domain.usecases

import android.util.Log
import com.georescue.victim.data.repository.SignalRepository
import com.georescue.victim.domain.models.SignalType
import javax.inject.Inject

/**
 * Use case responsible for triggering a distress signal.
 *
 * Called by:
 *  - [FailsafeTimer] when the 30-second countdown expires (auto INACTIVITY)
 *  - Future: SOS button in UI (manual SOS)
 *
 * Delegates to [SignalRepository] which writes to Firestore `signals/` collection.
 * The backend Cloud Function then creates the actual `incidents/` document.
 */
import com.georescue.victim.data.repository.IncidentObserver
import com.georescue.victim.domain.models.IncidentStatus
import javax.inject.Singleton

@Singleton
class SignalUseCase @Inject constructor(
    private val signalRepository: SignalRepository,
    private val incidentObserver: IncidentObserver
) {
    private var lastSignalTime = 0L

    suspend operator fun invoke(type: SignalType): Result<String> {
        val now = System.currentTimeMillis()
        if (now - lastSignalTime < 10000) {
            Log.d("SignalUseCase", "Debouncing signal (sent less than 10s ago)")
            return Result.failure(IllegalStateException("Too many requests"))
        }

        val currentIncident = incidentObserver.currentIncident.value
        if (currentIncident != null && currentIncident.status != IncidentStatus.RESOLVED) {
            Log.d("SignalUseCase", "Skipping signal — Incident already active")
            return Result.success("ALREADY_ACTIVE")
        }

        lastSignalTime = now
        Log.d("SignalUseCase", "Triggering signal: $type")
        return signalRepository.sendSignal(type)
            .onSuccess { id -> Log.d("SignalUseCase", "Signal sent successfully: $id") }
            .onFailure { e -> Log.e("SignalUseCase", "Signal failed: ${e.message}", e) }
    }

    /** Convenience alias kept for any future direct SOS trigger from UI. */
    suspend fun triggerSOS(): Result<String> = invoke(SignalType.SOS)
}

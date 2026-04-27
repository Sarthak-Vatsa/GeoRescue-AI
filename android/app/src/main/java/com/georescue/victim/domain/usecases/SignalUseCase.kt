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
class SignalUseCase @Inject constructor(
    private val signalRepository: SignalRepository
) {
    /**
     * Sends a signal of the given [type] to the backend.
     * Operator overload allows [FailsafeTimer] to call `signalUseCase(SignalType.SOS)` directly.
     */
    suspend operator fun invoke(type: SignalType): Result<String> {
        Log.d("SignalUseCase", "Triggering signal: $type")
        return signalRepository.sendSignal(type)
            .onSuccess { id -> Log.d("SignalUseCase", "Signal sent successfully: $id") }
            .onFailure { e -> Log.e("SignalUseCase", "Signal failed: ${e.message}", e) }
    }

    /** Convenience alias kept for any future direct SOS trigger from UI. */
    suspend fun triggerSOS(): Result<String> = invoke(SignalType.SOS)
}

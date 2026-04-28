package com.georescue.victim.domain.usecases

import android.util.Log
import com.georescue.victim.domain.models.SignalType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 30-second failsafe countdown timer. Fires an INACTIVITY signal when it expires.
 * Marked @Singleton so DetectionService and IncidentViewModel share the same instance
 * and observe the same [remainingTime] / [isActive] flows.
 */
@Singleton
class FailsafeTimer @Inject constructor(
    private val signalUseCase: SignalUseCase
) {
    companion object {
        const val COUNTDOWN_SECONDS = 30
    }

    private val _remainingTime = MutableStateFlow(COUNTDOWN_SECONDS)
    val remainingTime: StateFlow<Int> = _remainingTime.asStateFlow()

    private val _isActive = MutableStateFlow(false)
    /** True while the countdown is ticking. Drives SOSCountdown navigation state. */
    val isActive: StateFlow<Boolean> = _isActive.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer(scope: CoroutineScope) {
        if (timerJob?.isActive == true) return

        _remainingTime.value = COUNTDOWN_SECONDS
        _isActive.value = true
        timerJob = scope.launch {
            while (isActive && _remainingTime.value > 0) {
                delay(1000L)
                _remainingTime.value -= 1
            }

            if (_remainingTime.value == 0) {
                Log.d("TIMER", "Countdown expired — triggering INACTIVITY signal")
                signalUseCase.invoke(SignalType.INACTIVITY)
            }
            _isActive.value = false
        }
    }

    /** Called when new movement is detected — resets countdown but keeps timer running. */
    fun resetTimer() {
        timerJob?.cancel()
        timerJob = null
        _remainingTime.value = COUNTDOWN_SECONDS
        _isActive.value = false
    }

    /** Called when the user taps "I'm Safe" — cancels entirely. */
    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _remainingTime.value = COUNTDOWN_SECONDS
        _isActive.value = false
    }
}

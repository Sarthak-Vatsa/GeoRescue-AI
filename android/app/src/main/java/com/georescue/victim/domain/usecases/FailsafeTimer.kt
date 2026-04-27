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

class FailsafeTimer @Inject constructor(
    private val signalUseCase: SignalUseCase
) {
    private val _remainingTime = MutableStateFlow(1800)
    val remainingTime: StateFlow<Int> = _remainingTime.asStateFlow()

    private var timerJob: Job? = null

    fun startTimer(scope: CoroutineScope) {
        if (timerJob?.isActive == true) return
        
        _remainingTime.value = 1800
        timerJob = scope.launch {
            while (isActive && _remainingTime.value > 0) {
                delay(1000L)
                _remainingTime.value -= 1
            }

            //Log.d("TIMER", "Remaining: ${_remainingTime.value}")
            if (_remainingTime.value == 0) {
                // Time's up! Trigger SOS signal
                Log.d("TIMER", "Triggering SOS")
                signalUseCase.invoke(SignalType.SOS)
                //signalUseCase.triggerSOS()
            }
        }
    }

    fun resetTimer() {
        if (timerJob?.isActive == true) {
            _remainingTime.value = 30
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
        _remainingTime.value = 30
    }
}

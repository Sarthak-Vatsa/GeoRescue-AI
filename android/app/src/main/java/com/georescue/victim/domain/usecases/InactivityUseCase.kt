package com.georescue.victim.domain.usecases

import android.util.Log
import com.georescue.victim.data.repository.SensorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.math.pow

class InactivityUseCase @Inject constructor(
    private val sensorRepository: SensorRepository
) {
    // Emits true if immobilized, false otherwise
    fun monitorInactivity(): Flow<Boolean> = flow {
        val windowDurationMs = 10_000L // 10 seconds for testing
        val varianceThreshold = 0.5f
        val window = mutableListOf<Float>()
        val timestamps = mutableListOf<Long>()

        sensorRepository.getLinearAccelerationStream().collect { data ->
            val currentTime = data.timestamp
            window.add(data.magnitude)
            timestamps.add(currentTime)


            // Remove old samples outside the window duration
            while (timestamps.isNotEmpty() && (currentTime - timestamps.first()) > windowDurationMs) {
                timestamps.removeAt(0)
                window.removeAt(0)
            }

            // Only calculate variance if we have enough data (e.g., at least 3 seconds of data)
            if (timestamps.isNotEmpty() && (currentTime - timestamps.first()) > 3_000L) {
                val mean = window.average().toFloat()
                val variance = window.map { (it - mean).pow(2) }.average().toFloat()

                Log.d("InactivityTest", "Samples: ${window.size} | Mean: $mean | Variance: $variance")

                if (variance < varianceThreshold) {
                    Log.w("InactivityTest", "⚠️ IMMOBILIZED STATE DETECTED")
                    emit(true)
                } else {
                    emit(false)
                }
            } else {
                emit(false)
            }
        }
    }
}

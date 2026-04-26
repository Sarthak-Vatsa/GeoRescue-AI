package com.georescue.victim.data.repository

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import com.georescue.victim.domain.models.KineticData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import kotlin.math.sqrt

class SensorRepositoryImpl @Inject constructor(
    private val sensorManager: SensorManager
) : SensorRepository {

    override fun getLinearAccelerationStream(): Flow<KineticData> = callbackFlow {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        
        if (sensor == null) {
            close(Exception("Linear Acceleration sensor not available"))
            Log.d("Sensor_Repo", "Sensor not available")
            return@callbackFlow
        }

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event?.let {
                    if (it.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
                        val x = it.values[0]
                        val y = it.values[1]
                        val z = it.values[2]
                        
                        // Amag = sqrt(x^2 + y^2 + z^2)
                        val magnitude = sqrt((x * x + y * y + z * z).toDouble()).toFloat()

                        Log.d("SENSOR_TEST", "Mag: $magnitude")
                        
                        trySend(KineticData(x, y, z, magnitude))
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                // Not used
            }
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }
}

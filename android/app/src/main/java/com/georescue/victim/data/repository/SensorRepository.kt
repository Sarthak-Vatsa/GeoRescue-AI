package com.georescue.victim.data.repository

import com.georescue.victim.domain.models.KineticData
import kotlinx.coroutines.flow.Flow

interface SensorRepository {
    fun getLinearAccelerationStream(): Flow<KineticData>
}

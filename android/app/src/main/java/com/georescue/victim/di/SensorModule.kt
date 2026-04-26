package com.georescue.victim.di

import android.content.Context
import android.hardware.SensorManager
import com.georescue.victim.data.repository.SensorRepository
import com.georescue.victim.data.repository.SensorRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    fun provideSensorManager(@ApplicationContext context: Context): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @Provides
    @Singleton
    fun provideSensorRepository(sensorManager: SensorManager): SensorRepository {
        return SensorRepositoryImpl(sensorManager)
    }
}

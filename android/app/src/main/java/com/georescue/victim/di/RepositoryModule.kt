package com.georescue.victim.di

import com.georescue.victim.data.repository.SignalRepository
import com.georescue.victim.data.repository.SignalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module binding interface → implementation for data-layer repositories.
 * Uses @Binds (zero boilerplate) instead of @Provides since the impl is @Singleton injected.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSignalRepository(
        impl: SignalRepositoryImpl
    ): SignalRepository
}
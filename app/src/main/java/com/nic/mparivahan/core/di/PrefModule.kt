package com.nic.mparivahan.core.di

import com.nic.mparivahan.core.preference.PrefManager
import com.nic.mparivahan.core.preference.PrefManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefModule {

    @Binds
    @Singleton
    abstract fun bindPrefManager(impl: PrefManagerImpl): PrefManager
}
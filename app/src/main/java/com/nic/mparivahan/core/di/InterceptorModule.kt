package com.nic.mparivahan.core.di

import android.content.Context
import com.nic.mparivahan.core.preference.PrefManager
import com.nic.mparivahan.data.network.interceptor.AppLoggingInterceptor
import com.nic.mparivahan.data.network.interceptor.BodyEncryptionInterceptor
import com.nic.mparivahan.data.network.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {

    @Provides
    @Singleton
    fun provideBodyEncryptionInterceptor(@ApplicationContext context: Context): BodyEncryptionInterceptor = BodyEncryptionInterceptor(context)


    @Provides
    @Singleton
    fun provideHeaderInterceptor(prefManager: PrefManager): HeaderInterceptor = HeaderInterceptor(prefManager)


    @Provides
    @Singleton
    fun provideAppLoggingInterceptor(@ApplicationContext context: Context): AppLoggingInterceptor = AppLoggingInterceptor(context)

}
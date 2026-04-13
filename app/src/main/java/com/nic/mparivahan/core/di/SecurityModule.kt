package com.nic.mparivahan.core.di

import com.nic.mparivahan.data.network.security.AppCertificatePinner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Provides
    @Singleton
    fun provideCertificatePinner(): CertificatePinner = AppCertificatePinner().getPinner()

}
package com.nic.mparivahan.core.di

import com.nic.mparivahan.data.datasource.remote.api.CreateAccountServices
import com.nic.mparivahan.data.datasource.remote.api.NapixService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideNapixService(@RetrofitNapix retrofit: Retrofit): NapixService {
        return retrofit.create(NapixService::class.java)
    }

    @Provides
    @Singleton
    fun provideCreateAccountService(@RetrofitCreateAccount retrofit: Retrofit): CreateAccountServices {
        return retrofit.create(CreateAccountServices::class.java)
    }


}
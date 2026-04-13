package com.nic.mparivahan.core.di

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
object RetrofitModule {

    @Provides
    @Singleton
    @RetrofitNapix
    fun provideNapixRetrofit(@ClientNapix client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://delhigw.napix.gov.in")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @RetrofitCreateAccount
    fun provideCreateAccountRetrofit(@ClientCreateAccount client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://delhigw.napix.gov.in/nic/parivahan/mparivahan/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}

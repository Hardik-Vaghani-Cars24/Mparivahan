package com.nic.mparivahan.core.di

import com.nic.mparivahan.data.network.interceptor.AppLoggingInterceptor
import com.nic.mparivahan.data.network.interceptor.BodyEncryptionInterceptor
import com.nic.mparivahan.data.network.interceptor.HeaderInterceptor
import com.nic.mparivahan.data.network.security.AppCertificatePinner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    // region --- FireBase Token ---
    /** ----- Client -----*/
    @Provides
    @Singleton
    @ClientNapix
    fun provideNapixOkHttpClient(loggingInterceptor: AppLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build()
                chain.proceed(request)
            }

            .addInterceptor(loggingInterceptor)
            .build()
    }

    // region --- CreateAccount ---
    /** ----- Client -----*/
    @Provides
    @Singleton
    @ClientCreateAccount
    fun provideCreateAccountOkHttpClient(
        bodyEncryptionInterceptor: BodyEncryptionInterceptor,
        headerInterceptor: HeaderInterceptor,
        loggingInterceptor: AppLoggingInterceptor ,
        appCertificatePinner: AppCertificatePinner
    ): OkHttpClient {
        return OkHttpClient.Builder()
            // 🔹 Timeouts (match decompiled)
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

            // 🔹 1️⃣ Body Encryption Interceptor (i22) (modify body first)
            .addInterceptor(bodyEncryptionInterceptor) // 🔐

            // 🔹 2️⃣ Header Interceptor (tz) (add tokens after body ready)
            .addInterceptor(headerInterceptor) // 📡

            // 🔹 3️⃣ Logging/Custom Interceptor (aVar.e()) (ONLY ONCE, always last)
            .addInterceptor(loggingInterceptor) // 🪵 // CustomInterceptor()

            // 🔹 4️⃣ Certificate Pinner (aVar.s()) (not order-sensitive but keep clean)
            .certificatePinner(appCertificatePinner.getPinner()) // 🔒

            .build()
    }

}
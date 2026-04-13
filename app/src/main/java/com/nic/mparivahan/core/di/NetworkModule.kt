package com.nic.mparivahan.core.di;

import android.content.Context
//import com.nic.mparivahan.BuildConfig
import com.nic.mparivahan.core.preference.PrefManager
import com.nic.mparivahan.data.datasource.remote.api.CreateAccountServices
import com.nic.mparivahan.data.datasource.remote.api.NapixService
import com.nic.mparivahan.data.network.interceptor.AppLoggingInterceptor
import com.nic.mparivahan.data.network.interceptor.BodyEncryptionInterceptor
import com.nic.mparivahan.data.network.interceptor.HeaderInterceptor
import com.nic.mparivahan.data.network.security.AppCertificatePinner
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
object NetworkModule {}
















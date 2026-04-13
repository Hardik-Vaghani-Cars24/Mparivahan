package com.nic.mparivahan.core.di

import com.nic.mparivahan.core.datastore.RequestResponseDataStore
import com.nic.mparivahan.core.dispatchers.IoDispatcher
import com.nic.mparivahan.core.util.NetworkHelper
import com.nic.mparivahan.domain.repository.ApiPortalRepository
import com.nic.mparivahan.data.repository.ApiPortalRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiPortalModule {

    @Provides
    @Singleton
    fun provideApiPortal(
        @ClientCreateAccount okHttpClient: OkHttpClient,
        networkHelper: NetworkHelper ,
        mDataStore: RequestResponseDataStore ,
        @IoDispatcher  io: CoroutineDispatcher): ApiPortalRepository {

        return ApiPortalRepositoryImpl(
            okHttpClient,
            networkHelper,
            mDataStore,
            io
        )
    }
}
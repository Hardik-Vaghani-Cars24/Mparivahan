package com.nic.mparivahan.core.di;

import com.nic.mparivahan.data.datasource.remote.api.NapixService
import com.nic.mparivahan.data.repository.ApiPortalRepositoryImpl
import com.nic.mparivahan.data.repository.CreateAccountRepositoryImpl
import com.nic.mparivahan.data.repository.FireBaseRepositoryImpl
import com.nic.mparivahan.domain.repository.ApiPortalRepository
import com.nic.mparivahan.domain.repository.CreateAccountRepository
import com.nic.mparivahan.domain.repository.FireBaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFireBaseRepository(impl: FireBaseRepositoryImpl): FireBaseRepository

    @Binds
    @Singleton
    abstract fun bindCreateAccountRepository(impl: CreateAccountRepositoryImpl): CreateAccountRepository

//    @Binds
//    @Singleton
//    abstract fun bindApiPortalRepository(impl: ApiPortalRepositoryImpl): ApiPortalRepository

}
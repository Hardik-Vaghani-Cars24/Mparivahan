package com.nic.mparivahan.core.di;

import com.nic.mparivahan.core.dispatchers.DefaultDispatcher
import com.nic.mparivahan.core.dispatchers.IoDispatcher
import com.nic.mparivahan.core.dispatchers.MainDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}

/*
@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds abstract fun bindDispatchers(impl: AppDispatchersImpl): AppDispatchers
}
*/

package com.nic.mparivahan.core.di

import javax.inject.Qualifier

//------
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitDefault

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClientDefault

//------
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitNapix

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClientNapix


//------
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitCreateAccount

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ClientCreateAccount


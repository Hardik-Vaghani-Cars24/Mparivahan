package com.nic.mparivahan.core.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {
//
//    @Provides
//    @Singleton
//    fun provideFirebaseApp(
//        @ApplicationContext context: Context
//    ): FirebaseApp {
//
//        return if (FirebaseApp.getApps(context).isEmpty()) {
//
//            val options = FirebaseOptions.Builder()
//                .setApplicationId("1:579826174379:android:61bcc559cc66612024aaee")
//                .setApiKey("AIzaSyDCa1ftRI95eSwoICc_BXr34b4bUSTqAk8")
//                .setProjectId("nextgen-mparivahan-2025")
//                .setGcmSenderId("579826174379")
//                .build()
//
//            FirebaseApp.initializeApp(context, options)
//
//        } else {
//            FirebaseApp.getInstance()
//        }
//    }
//
//    @Provides
//    @Singleton
//    fun provideFirebaseMessaging(): FirebaseMessaging {
//        return FirebaseMessaging.getInstance()
//    }
}



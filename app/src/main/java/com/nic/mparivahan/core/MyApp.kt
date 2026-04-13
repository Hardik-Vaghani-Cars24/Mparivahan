package com.nic.mparivahan.core

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /*FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:1234567890:android:abc123")
                .setApiKey("AIzaSyXXXXXXX")
                .setProjectId("your-project-id")
                .setGcmSenderId("1234567890")
                .build();*/
       /* val options = FirebaseOptions.Builder()
            .setApplicationId("1:579826174379:android:61bcc559cc66612024aaee")
            .setApiKey("AIzaSyDCa1ftRI95eSwoICc_BXr34b4bUSTqAk8")
            .setProjectId("nextgen-mparivahan-2025")
            .setGcmSenderId("579826174379")
            .build()

        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this, options)
        }*/
    }
}
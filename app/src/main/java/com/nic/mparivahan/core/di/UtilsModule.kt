package com.nic.mparivahan.core.di;

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
//import com.devtools.hardikinterveiw.core.util.NetworkHelper
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.google.gson.Gson
import com.nic.mparivahan.core.datastore.NapixDataStore
import com.nic.mparivahan.core.dispatchers.IoDispatcher
import com.nic.mparivahan.core.preference.PrefManager
import com.nic.mparivahan.core.preference.PrefManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.text.SimpleDateFormat
import java.util.Locale

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            scope = CoroutineScope(ioDispatcher + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile("napix_datastore") }
        )
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("app_pref", Context.MODE_PRIVATE)

//    @Provides
//    @Singleton
//    fun providePrefManager(sharedPreferences: SharedPreferences, dataStore: NapixDataStore): PrefManager { return PrefManagerImpl(sharedPreferences) }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    fun provideDateFormatter(): SimpleDateFormat =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    /**
     * UtilsModule →
     * - SharedPreferences / DataStore
     * - Gson
     * - DateFormatter
     * - System services (ClipboardManager, NotificationManager, AlarmManager, LocationManager)
     * - External SDK objects
     * */

}

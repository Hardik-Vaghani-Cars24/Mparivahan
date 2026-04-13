package com.nic.mparivahan.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import com.nic.mparivahan.domain.model.NapixTokenModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NapixDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
) : BaseDataStore<NapixTokenModel>(
    dataStore = dataStore,
    gson = gson,
    clazz = NapixTokenModel::class.java
)








/*
{

    // 🔹 Save JSON (dynamic key support)
    suspend fun saveResponse(
        response: NapixTokenModel,
        key: Preferences.Key<String> = DataStoreKeys.TOKEN_DEFAULT_JSON
    ) {
        val json = gson.toJson(response)

        dataStore.edit { pref ->
            pref[key] = json
        }
    }

    // 🔹 Get JSON → Object (dynamic key)
    fun getResponse(
        key: Preferences.Key<String> = DataStoreKeys.TOKEN_DEFAULT_JSON
    ): Flow<NapixTokenModel?> {
        return dataStore.data.map { pref ->
            val json = pref[key]
            json?.let {
                gson.fromJson(it, NapixTokenModel::class.java)
            }
        }
    }

    // 🔹 Get raw JSON
    fun getRawJson(
        key: Preferences.Key<String> = DataStoreKeys.TOKEN_DEFAULT_JSON
    ): Flow<String?> {
        return dataStore.data.map { pref ->
            pref[key]
        }
    }

    // 🔹 Clear specific token
    suspend fun clearKey(
        key: Preferences.Key<String> = DataStoreKeys.TOKEN_DEFAULT_JSON
    ) {
        dataStore.edit { pref ->
            pref.remove(key)
        }
    }
}*/

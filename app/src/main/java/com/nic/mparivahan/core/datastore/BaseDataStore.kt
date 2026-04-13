package com.nic.mparivahan.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
abstract class BaseDataStore<T>(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson,
    private val clazz: Class<T>
) {

    // 🔹 Save JSON (dynamic key support)
    suspend fun save(data: T, key: Preferences.Key<String>) {
        val json = gson.toJson(data)
        dataStore.edit { it[key] = json }
    }

    // 🔹 Get JSON → Object (dynamic key)
    fun get(key: Preferences.Key<String>): Flow<T?> {
        return dataStore.data.map { pref ->
            pref[key]?.let { json ->
                gson.fromJson(json, clazz)
            }
        }
    }

    // 🔹 Get raw JSON
    fun getRaw(key: Preferences.Key<String>): Flow<String?> {
        return dataStore.data.map { it[key] }
    }

    // 🔹 Clear specific token
    suspend fun clear(key: Preferences.Key<String>) {
        dataStore.edit { it.remove(key) }
    }
}

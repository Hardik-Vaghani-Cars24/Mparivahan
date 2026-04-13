package com.nic.mparivahan.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.nic.mparivahan.domain.model.SecurityModel
import javax.inject.Inject

class RequestResponseDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val gson: Gson
)  : BaseDataStore<SecurityModel>(
    dataStore = dataStore,
    gson = gson,
    clazz = SecurityModel::class.java
)
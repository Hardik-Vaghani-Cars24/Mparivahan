package com.nic.mparivahan.core.preference

import android.content.SharedPreferences
import com.nic.mparivahan.core.datastore.NapixDataStore
import com.nic.mparivahan.data.network.TokenType
import com.nic.mparivahan.data.network.toDataStoreKey
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import androidx.core.content.edit

class PrefManagerImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
//    private val mDataStore: NapixDataStore,
) : PrefManager {

    private fun getAccessDefaultToken(): String { return sharedPreferences.getString("access_token_default", "") ?: "" }
    private fun getAccessChallanToken(): String { return sharedPreferences.getString("access_token_challan", "") ?: ""}
    private fun getAccessSarathiToken(): String { return sharedPreferences.getString("access_token_sarathi", "") ?: ""}

    override fun getAccessToken(type: Int): String {
        return when (type) {
            1 -> getAccessSarathiToken()
            2 -> getAccessChallanToken()
            else -> {getAccessDefaultToken()}
        }
    }

    private fun setAccessDefaultToken(token: String) { sharedPreferences.edit { putString("access_token_default", token) } }

    private fun setAccessChallanToken(token: String) { sharedPreferences.edit { putString("access_token_challan", token) } }

    private fun setAccessSarathiToken(token: String) { sharedPreferences.edit { putString("access_token_sarathi", token) } }

    override fun setAccessToken(type: Int, token: String) {
        when (type) {
            1 -> setAccessSarathiToken(token)
            2 -> setAccessChallanToken(token)
            else -> setAccessDefaultToken(token)
        }
    }

    override fun getCitizenToken(): String { return sharedPreferences.getString("citizen_token", "") ?: "" }

    override fun setCitizenToken(token: String) { sharedPreferences.edit { putString("citizen_token", token) } }

    override fun isLoggedIn(): Boolean { return sharedPreferences.getBoolean("is_logged_in", false) }

    override fun setLoggedIn(isLoggedIn: Boolean) { sharedPreferences.edit { putBoolean("is_logged_in", isLoggedIn) } }
}









/*
override suspend fun getAccessToken(tokenType: TokenType): String {
        val data = mDataStore.get(tokenType.toDataStoreKey())
        val tokenModel = data.firstOrNull()
        return tokenModel?.access_token ?: ""
    }*/
//        return when (tokenType) {
//            TokenType.DEFAULT -> sharedPreferences.getString("access_token_default", "") ?: ""
//            TokenType.SARATHI -> sharedPreferences.getString("access_token_sarathi", "") ?: ""
//            TokenType.CHALLAN -> sharedPreferences.getString("access_token_challan", "") ?: ""
//        }

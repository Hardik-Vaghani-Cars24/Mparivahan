package com.nic.mparivahan.core.preference

import com.nic.mparivahan.data.network.TokenType

interface PrefManager {
    fun getAccessToken(type: Int = 0): String
    fun setAccessToken(type: Int = 0, token: String)
    fun getCitizenToken(): String
    fun setCitizenToken(token: String)
    fun isLoggedIn(): Boolean
    fun setLoggedIn(isLoggedIn: Boolean)
}
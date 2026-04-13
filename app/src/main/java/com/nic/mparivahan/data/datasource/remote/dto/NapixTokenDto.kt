package com.nic.mparivahan.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class NapixTokenDto(
    @SerializedName("token_type") val tokenType: String? = null,
    @SerializedName("access_token") val accessToken: String? = null,
    @SerializedName("scope") val scope: String? = null,
    @SerializedName("expires_in") val expiresIn: Int = 0,
    @SerializedName("consented_on") val consentedOn: Long = 0
)



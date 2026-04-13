package com.nic.mparivahan.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow

data class NapixTokenModel(
    val access_token: String? = null,
    val consented_on: Long = 0,
    val expires_in: Int = 0,
    val scope: String? = null,
    val token_type: String? = null,
)
package com.nic.mparivahan.data.network

import androidx.datastore.preferences.core.Preferences
import com.nic.mparivahan.core.datastore.DataStoreKeys

enum class TokenType(
    val grantType: String,
    val scope: String,
    val clientId: String,
    val clientSecret: String
) {
    DEFAULT(
        grantType = "client_credentials",
        scope = "napix",
        clientId = "b91c303443f61b37106750823881cd2f",
        clientSecret = "de83eeeb148878ae375f28756492e8a0"
    ),

    SARATHI(
        grantType = "client_credentials",
        scope = "napix",
        clientId = "3e395e88f29ed880bcace2faec9f2e7b",
        clientSecret = "899f535e4fae7bbbfe2c9eeb0eb0344b"
    ),

    CHALLAN(
    grantType = "client_credentials",
    scope = "napix",
    clientId = "28a460ce9655446161945ddfd59ead65",
    clientSecret = "5b9992f611625ba1a38e0af88a809e62"
    )
}

fun TokenType.toDataStoreKey(): Preferences.Key<String> {
    return when (this) {
        TokenType.DEFAULT -> DataStoreKeys.TOKEN_DEFAULT_JSON
        TokenType.SARATHI -> DataStoreKeys.TOKEN_SARATHI_JSON
        TokenType.CHALLAN -> DataStoreKeys.TOKEN_CHALLAN_JSON
    }
}

fun TokenType.getIndexInt(): Int {
    return when (this) {
        TokenType.DEFAULT -> 0
        TokenType.SARATHI -> 1
        TokenType.CHALLAN -> 3
    }
}
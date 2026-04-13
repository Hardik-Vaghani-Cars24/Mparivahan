package com.nic.mparivahan.core.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    // region NapixDataStore
    val TOKEN_DEFAULT_JSON = stringPreferencesKey("token_default_json")
    val TOKEN_SARATHI_JSON = stringPreferencesKey("token_sarathi_json")
    val TOKEN_CHALLAN_JSON = stringPreferencesKey("token_challan_json")
    // endregion

    // region 400 Bad request
    val REQUEST_RESPONSE_JSON = stringPreferencesKey("bad_request_400_json")
    // endregion
}

package com.nic.mparivahan.domain.usecase

import com.nic.mparivahan.core.datastore.RequestResponseDataStore
import com.nic.mparivahan.core.datastore.DataStoreKeys
import com.nic.mparivahan.domain.model.SecurityModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoredRequestResponseTokenUseCase @Inject constructor(
    private val mDataStore: RequestResponseDataStore ,
) {
    operator fun invoke(): Flow<SecurityModel?> {
        return mDataStore.get(DataStoreKeys.REQUEST_RESPONSE_JSON)
    }
}

package com.nic.mparivahan.domain.usecase

import com.nic.mparivahan.core.datastore.NapixDataStore
import com.nic.mparivahan.data.network.TokenType
import com.nic.mparivahan.data.network.toDataStoreKey
import com.nic.mparivahan.domain.model.NapixTokenModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoredTokenUseCase @Inject constructor(
    private val mDataStore: NapixDataStore,
) {
    operator fun invoke(tokenType: TokenType): Flow<NapixTokenModel?> {
        return mDataStore.get(tokenType.toDataStoreKey())
    }
}

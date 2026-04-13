package com.nic.mparivahan.data.repository

import android.util.Log
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.datastore.RequestResponseDataStore
import com.nic.mparivahan.core.datastore.DataStoreKeys
import com.nic.mparivahan.core.dispatchers.IoDispatcher
import com.nic.mparivahan.core.util.NetworkHelper
import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.data.datasource.remote.api.CreateAccountServices
import com.nic.mparivahan.data.mapper.toModel
import com.nic.mparivahan.domain.model.SecurityModel
import com.nic.mparivahan.domain.repository.CreateAccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import javax.inject.Inject

class CreateAccountRepositoryImpl @Inject constructor(
    private val api: CreateAccountServices ,
    private val networkHelper: NetworkHelper ,
    private val mDataStore: RequestResponseDataStore ,
    @IoDispatcher private val io: CoroutineDispatcher ,
) : CreateAccountRepository {
    private val TAG = CreateAccountRepositoryImpl::class.java.simpleName

    override fun getStateMasterList(
        url: String,
        request: RequestBody,
        timestamp: String
    ): Flow<Resource<SecurityModel>> = flow {
        emit(Resource.Loading)

        try {
            if (!networkHelper.isInternetAvailable()) {
                throw Exception("No Internet")
            }
            val response = api.getStateMasterList(
//                url = url,
                request = request,
                timestamp = timestamp
            ).toModel()

            //log(TAG, State.RESPONSE.value) { "getStateMasterList: $response" }

            // 🔥 Store based on Key
            mDataStore.save(response, DataStoreKeys.REQUEST_RESPONSE_JSON)

            emit(Resource.Success(response))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }

    }

}
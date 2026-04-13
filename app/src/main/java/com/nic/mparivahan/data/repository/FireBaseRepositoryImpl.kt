package com.nic.mparivahan.data.repository

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.datastore.NapixDataStore
import com.nic.mparivahan.core.di.FirebaseModule
import com.nic.mparivahan.core.dispatchers.IoDispatcher
import com.nic.mparivahan.core.preference.PrefManager
import com.nic.mparivahan.core.util.NetworkHelper
import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.data.datasource.remote.api.NapixService
import com.nic.mparivahan.data.datasource.remote.dto.NapixTokenDto
import com.nic.mparivahan.data.mapper.AppMapper
import com.nic.mparivahan.data.mapper.toDto
import com.nic.mparivahan.data.mapper.toModel
import com.nic.mparivahan.data.network.TokenType
import com.nic.mparivahan.data.network.getIndexInt
import com.nic.mparivahan.data.network.toDataStoreKey
import com.nic.mparivahan.domain.model.NapixTokenModel
import com.nic.mparivahan.domain.repository.FireBaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireBaseRepositoryImpl @Inject constructor(
    private val api: NapixService,
//    private val firebaseMessaging: FirebaseMessaging,
//    private val dao: AppDao,
    private val networkHelper: NetworkHelper,
    private val mDataStore: NapixDataStore,
    private val mapper: AppMapper,
    @IoDispatcher private val io: CoroutineDispatcher,
    private val prefManager: PrefManager,
) : FireBaseRepository {
    private val TAG = FireBaseRepositoryImpl::class.java.simpleName

    override fun getToken(tokenType: TokenType): Flow<Resource<NapixTokenModel>> = flow {
        emit(Resource.Loading)

        try {
            if (!networkHelper.isInternetAvailable()) {
                throw Exception("No Internet")
            }

            val response = api.getToken(
                grant_type = tokenType.grantType,
                scope = tokenType.scope,
                client_id = tokenType.clientId,
                client_secret = tokenType.clientSecret
            ).toModel()

            // 🔥 Store based on TokenType
            prefManager.setAccessToken(type = tokenType.getIndexInt(), token = response.access_token ?: "")
            mDataStore.save(response, tokenType.toDataStoreKey())

            //log(TAG, State.RESPONSE.value) { "response: $response" }
            emit(Resource.Success(response))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }.flowOn(io)
}

    /*
    override fun getToken(tokenType: TokenType): Flow<Resource<NapixTokenModel>> = flow {
        emit(Resource.Loading)

        try {
            if (!networkHelper.isInternetAvailable()) {
                throw Exception("No Internet")
            }

            val token = firebaseMessaging.token.await()
            Log.d(TAG, "FCM_TOKEN ├─→ $token")

            // If you want to map it
            val model: NapixTokenModel = NapixTokenModel(token)
            emit(Resource.Success(model))

        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error"))
        }
    }.flowOn(io)*/


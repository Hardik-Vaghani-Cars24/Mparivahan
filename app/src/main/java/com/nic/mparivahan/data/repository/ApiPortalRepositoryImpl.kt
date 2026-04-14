package com.nic.mparivahan.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.nic.mparivahan.core.common.EncryptionDecryption
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.base64Decode
import com.nic.mparivahan.core.common.decode64
import com.nic.mparivahan.core.common.decryptWithAES
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.common.toModel
import com.nic.mparivahan.core.datastore.RequestResponseDataStore
import com.nic.mparivahan.core.di.ClientCreateAccount
import com.nic.mparivahan.core.dispatchers.IoDispatcher
import com.nic.mparivahan.core.util.NetworkHelper
import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.data.datasource.remote.dto.SendOtpResult
import com.nic.mparivahan.domain.repository.ApiPortalRepository
import com.nic.mparivahan.domain.model.SecurityModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.lang.reflect.Type
import javax.inject.Inject
import kotlin.jvm.java

class ApiPortalRepositoryImpl @Inject constructor(
    @ClientCreateAccount private val okHttpClient: OkHttpClient ,
    private val networkHelper: NetworkHelper ,
    private val mDataStore: RequestResponseDataStore ,
    @IoDispatcher private val io: CoroutineDispatcher ,
) : ApiPortalRepository {
    private val TAG = ApiPortalRepositoryImpl::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(IOException::class)
    override fun <T> post(url: String, json: String, timeStamp: String): Flow<Resource<T>> = flow {

        emit(Resource.Loading)

        if (!networkHelper.isInternetAvailable()) {
            emit(Resource.Error("No Internet"))
            return@flow
        }

        val body = json
            .trimIndent()
            .toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(body)
            .addHeader("timestamp" , timeStamp)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (! response.isSuccessful) {
                emit(Resource.Error("HTTP ${response.code}"))
                return@flow
            }

            val raw = response.body.string()

            if (raw.isEmpty()) {
                emit(Resource.Error("Empty body"))
                return@flow
            }

            val appResponse = Gson().fromJson(raw , SecurityModel::class.java)

            //val decoded = appResponse.data?.let { String(base64Decode(it), Charsets.UTF_8) } ?: ""
            //val decryptedValue : String = EncryptionDecryption().a(decoded , timeStamp).toString()
            val decoded = try {
                appResponse.data?.decode64()
            } catch (e: Exception) {
                log(TAG, State.ERROR.value) { "Base64 decode failed: ${e.message}" }
                return@flow
            }

            val decryptedValue = try {
                decoded?.decryptWithAES(timeStamp)
            } catch (e: Exception) {
                log(TAG, State.ERROR.value) { "Decryption failed: ${e.message}" }
                return@flow
            }

            if (decryptedValue.isNullOrEmpty()) {
                log(TAG, State.ERROR.value) { "Decrypted data is null" }
                return@flow
            }

            @Suppress("UNCHECKED_CAST")
            val result = decryptedValue as T
            log(TAG, State.SUCCESS.value) { "post:Success $result" }

            emit(Resource.Success(result))
        }


    }.flowOn(io)
}
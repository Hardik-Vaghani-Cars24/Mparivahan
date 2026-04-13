package com.nic.mparivahan.data.network.interceptor

import android.content.Context
import com.google.gson.Gson
import com.nic.mparivahan.core.common.EncryptionDecryption
import com.nic.mparivahan.data.datasource.remote.dto.SecurityDto
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.Buffer
import javax.inject.Inject

class BodyEncryptionInterceptor @Inject constructor(context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val originalBody = originalRequest.body

        val timestamp: String = originalRequest.header("timestamp") ?: "" // -> String strD = kc5VarRequest.d(h);

        return if (originalBody != null) {

            val buffer = Buffer() // -> u70 u70Var = new u70();
            originalBody.writeTo(buffer) // -> lc5VarA.writeTo(u70Var);

            val bodyString: String = buffer.readUtf8() // -> String strH0 = u70Var.H0();

            // 🔐 TODO: Apply your encryption logic here

            val encryptedBody = EncryptionDecryption().b(bodyString, timestamp) // 2.0.135

            val model = SecurityDto(encryptedBody)

            val json = Gson().toJson(model)

            val mediaType = "application/json; charset=utf-8".toMediaType()

            val newBody: RequestBody = json.toRequestBody(mediaType)

            val newRequest = originalRequest
                .newBuilder()
                .header("Content-Type", "application/json")
                .header("Content-Length", newBody.contentLength().toString())
                .method(
                    originalRequest.method,
                    newBody
                )
                .build()

            chain.proceed(newRequest)

        } else {
            chain.proceed(originalRequest)
        }
    }
}
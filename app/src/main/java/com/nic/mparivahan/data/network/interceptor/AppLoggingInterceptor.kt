package com.nic.mparivahan.data.network.interceptor

import android.content.Context
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.getEndpoint
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class AppLoggingInterceptor @Inject constructor(context: Context): Interceptor {
    val TAG = "RetrofitLog"

    /**
     Options:
     NONE: no logging
     BASIC: method + URL + status
     HEADERS: BASIC + headers
     BODY: HEADERS + body (most verbose)
    */

    // 🔥 Recommended (prod safe) level = if (BuildConfig.DEBUG) { HttpLoggingInterceptor.Level.BODY } else { HttpLoggingInterceptor.Level.NONE }
    //fun get(): HttpLoggingInterceptor { return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        log(TAG, State.CALL.value) { "➡️ URL: ${request.url}" }
        log(TAG, State.CALL.value) { "➡️ Method: ${request.method}" }
        log(TAG, State.CALL.value) { "➡️ Headers: ${request.headers}" }

        // 🔥 Request Body print
        request.body?.let { body ->
            try {
                val buffer = okio.Buffer()
                body.writeTo(buffer)
                val bodyString = buffer.readUtf8()

                //log(TAG, State.DATA.value) { "➡️ Body: $bodyString" }
            } catch (e: Exception) {
                log(TAG, State.ERROR.value) { "⚠️ Body parse error: ${e.message}" }
            }
        }

        val response = chain.proceed(request)

        log(TAG, State.DATA.value) { "⬅️ Response Code: ${response.code} -> [${getEndpoint(url = request.url.toString())}]" }

        // 🔥 Response Body print
        response.body?.let { responseBody ->
            val source = responseBody.source()
            source.request(Long.MAX_VALUE) // Buffer the entire body
            val buffer = source.buffer

            val responseString = buffer.clone().readUtf8()
            log(TAG, State.DATA.value) { "⬅️ Response Body: $responseString" }
        }

        return response
    }
}
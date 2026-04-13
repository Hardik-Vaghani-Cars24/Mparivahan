package com.nic.mparivahan.data.network.interceptor

import android.util.Log
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.preference.PrefManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val prefManager: PrefManager
) : Interceptor {
    private val TAG = HeaderInterceptor::class.simpleName.toString()

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val builder = originalRequest.newBuilder()


        val token = prefManager.getAccessToken() // 🔹 Authorization
        log(TAG, State.PROCESS.value) { "intercept: $token" }

        // 🔹 Param1 (based on login)
        val param1Value = if (prefManager.isLoggedIn()) prefManager.getCitizenToken() else ""
        val newBuilder = originalRequest.newBuilder()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Authorization", "Bearer $token") // change dynamically
            .header("Param2", "2.0.135")  // 🔹 Param2 (static version)
            .header("Param1", param1Value)
            .removeHeader("Expect")

        if (originalRequest.body != null) {
            newBuilder.header("Content-Type", "application/json")
        }

        val newRequest = newBuilder.build()
        return chain.proceed(newRequest)
    }
}
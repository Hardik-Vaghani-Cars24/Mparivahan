package com.nic.mparivahan.data.datasource.remote.api

import com.nic.mparivahan.data.datasource.remote.dto.SecurityDto
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Url

interface CreateAccountServices {


    @POST("/masterapi/service/getStateMaster")
    @Headers("Content-Type: application/json", "Accept: application/json")
    suspend fun getStateMasterList(
//        @Url url: String?,
        @Body request: RequestBody?,
        @Header("timestamp") timestamp: String?
    ): SecurityDto //SecurityModel


/*

    @POST
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun getStateMasterList(
        @Url url: String?,
        @Body request: lc5?,
        @Header("timestamp") timestamp: String?
    ): Call<SecurityModle?>?*/
    // └── https://delhigw.napix.gov.in/nic/parivahan/mparivahan/masterapi/service/getStateMaster
}
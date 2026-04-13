package com.nic.mparivahan.data.datasource.remote.api

import com.nic.mparivahan.data.datasource.remote.dto.NapixTokenDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface NapixService {

/*    @FormUrlEncoded
    @POST("nic/parivahan/oauth2/token")
    fun getToken( // getChallNapix/getSarathiToken/getToken
        @Field("grant_type") grant_type: String?,  // client_credentials
        @Field("scope") scope: String?,  // napix
        @Field("client_id") client_id: String?,  // F6SVq3ha/5mIM2Ic5sZ7g/SyzPdAQCHgcHYhpTKbLzuY85LNTkzygfZJWTR+ZP2w
        @Field("client_secret") client_secret: String? // UqOL06PEZ870umUvb6mwp9CpXgh4ZUncnUEADDM/Dm+Y85LNTkzygfZJWTR+ZP2w
    ): Call<NapixTokenModel?>?*/
    // └──

    @FormUrlEncoded
    @POST("/nic/parivahan/oauth2/token")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun getToken(
        @Field("grant_type") grant_type: String?,
        @Field("scope") scope: String?,
        @Field("client_id") client_id: String?,
        @Field("client_secret") client_secret: String?
    ): NapixTokenDto

}
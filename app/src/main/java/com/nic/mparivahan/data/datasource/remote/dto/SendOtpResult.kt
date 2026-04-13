package com.nic.mparivahan.data.datasource.remote.dto

data class SendOtpResult(
    val param: String?,
    val recordId: Int,
    val statusCode: String?,
    val statusDesc: String?
)
package com.nic.mparivahan.domain.repository

import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.domain.model.SecurityModel
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface CreateAccountRepository {
    fun getStateMasterList(url: String, request: RequestBody, timestamp: String): Flow<Resource<SecurityModel>>
}
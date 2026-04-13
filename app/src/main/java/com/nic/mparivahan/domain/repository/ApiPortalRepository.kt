package com.nic.mparivahan.domain.repository

import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.domain.model.SecurityModel
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.lang.reflect.Type

interface ApiPortalRepository {
    fun <T> post(
        url: String,
        json: String,
        timeStamp: String,
    ): Flow<Resource<T>>
}
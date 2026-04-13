package com.nic.mparivahan.domain.usecase

import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.domain.repository.ApiPortalRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.lang.reflect.Type
import javax.inject.Inject

class ApiPortalUseCase @Inject constructor(
    val repository: ApiPortalRepository,
) {

    operator fun <T> invoke(
        url: String,
        jsonRequest: String,
        timeStamp: String,
    ) = repository.post<T>(url = url, json = jsonRequest, timeStamp = timeStamp)

}

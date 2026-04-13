package com.nic.mparivahan.domain.usecase

import com.nic.mparivahan.domain.repository.ApiPortalRepository
import com.nic.mparivahan.domain.repository.CreateAccountRepository
import okhttp3.RequestBody
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val repository: CreateAccountRepository
) {
    operator fun invoke(url: String, request: RequestBody, timestamp: String) = repository.getStateMasterList(url = url, request = request, timestamp = timestamp)
}

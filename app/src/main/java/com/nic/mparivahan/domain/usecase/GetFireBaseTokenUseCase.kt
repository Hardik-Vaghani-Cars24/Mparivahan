package com.nic.mparivahan.domain.usecase

import com.nic.mparivahan.data.network.TokenType
import com.nic.mparivahan.domain.repository.CreateAccountRepository
import com.nic.mparivahan.domain.repository.FireBaseRepository
import javax.inject.Inject


class GetFireBaseTokenUseCase @Inject constructor(
    private val repository: FireBaseRepository
) {
    operator fun invoke(tokenType: TokenType = TokenType.DEFAULT) = repository.getToken(tokenType = tokenType)
}


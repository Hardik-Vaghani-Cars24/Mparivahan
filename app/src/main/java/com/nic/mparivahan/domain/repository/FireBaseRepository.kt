package com.nic.mparivahan.domain.repository

import com.nic.mparivahan.core.util.Resource
import com.nic.mparivahan.data.datasource.remote.dto.NapixTokenDto
import com.nic.mparivahan.data.network.TokenType
import com.nic.mparivahan.domain.model.NapixTokenModel
import kotlinx.coroutines.flow.Flow

interface FireBaseRepository {
    fun getToken(tokenType: TokenType): Flow<Resource<NapixTokenModel>>

}
package com.nic.mparivahan.data.mapper

import com.nic.mparivahan.data.datasource.remote.dto.NapixTokenDto
import com.nic.mparivahan.domain.model.NapixTokenModel

fun NapixTokenDto.toModel(): NapixTokenModel {
    return NapixTokenModel(
        access_token = accessToken,
        consented_on = consentedOn,
        expires_in = expiresIn,
        scope = scope,
        token_type = tokenType
    )
}

fun NapixTokenModel.toDto(): NapixTokenDto {
    return NapixTokenDto(
        tokenType = token_type,
        accessToken = access_token,
        scope = scope,
        expiresIn = expires_in,
        consentedOn = consented_on
    )
}
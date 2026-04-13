package com.nic.mparivahan.data.mapper

import com.nic.mparivahan.data.datasource.remote.dto.SecurityDto
import com.nic.mparivahan.domain.model.SecurityModel

fun SecurityDto.toModel(): SecurityModel {
    return SecurityModel(data = data)
}

fun SecurityModel.toDto(): SecurityDto {
    return SecurityDto(data = data)
}
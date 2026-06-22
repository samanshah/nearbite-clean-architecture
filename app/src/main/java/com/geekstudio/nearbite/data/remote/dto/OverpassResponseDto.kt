package com.geekstudio.nearbite.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OverpassResponseDto(
    @SerialName("elements")
    val elements: List<OverpassElementDto> = emptyList()
)
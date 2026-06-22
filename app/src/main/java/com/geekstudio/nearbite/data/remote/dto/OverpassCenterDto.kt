package com.geekstudio.nearbite.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OverpassCenterDto(
    @SerialName("lat")
    val lat: Double? = null,

    @SerialName("lon")
    val lon: Double? = null
)
package com.geekstudio.nearbite.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OverpassElementDto(
    @SerialName("type")
    val type: String? = null,

    @SerialName("id")
    val id: Long? = null,

    @SerialName("lat")
    val lat: Double? = null,

    @SerialName("lon")
    val lon: Double? = null,

    @SerialName("center")
    val center: OverpassCenterDto? = null,

    @SerialName("tags")
    val tags: OverpassTagsDto? = null
)
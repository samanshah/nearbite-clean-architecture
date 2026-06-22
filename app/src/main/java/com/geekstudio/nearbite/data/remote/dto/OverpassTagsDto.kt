package com.geekstudio.nearbite.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OverpassTagsDto(
    @SerialName("name")
    val name: String? = null,

    @SerialName("amenity")
    val amenity: String? = null,

    @SerialName("cuisine")
    val cuisine: String? = null,

    @SerialName("addr:street")
    val street: String? = null,

    @SerialName("addr:housenumber")
    val houseNumber: String? = null,

    @SerialName("addr:city")
    val city: String? = null,

    @SerialName("opening_hours")
    val openingHours: String? = null,

    @SerialName("phone")
    val phone: String? = null,

    @SerialName("website")
    val website: String? = null
)
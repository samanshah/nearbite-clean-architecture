package com.geekstudio.nearbite.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(

    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("image_url")
    val imageUrl: String?,

    @SerialName("rating")
    val rating: Double?,

    @SerialName("categories")
    val categories: List<CategoryDto>?,

    @SerialName("coordinates")
    val coordinates: CoordinatesDto?,

    @SerialName("location")
    val location: LocationDto?

)
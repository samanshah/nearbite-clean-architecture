package com.geekstudio.nearbite.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchRestaurantsResponseDto(

    @SerialName("businesses")
    val restaurants: List<RestaurantDto>

)
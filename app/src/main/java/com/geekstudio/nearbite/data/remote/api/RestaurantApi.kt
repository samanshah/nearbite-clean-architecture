package com.geekstudio.nearbite.data.remote.api

import com.geekstudio.nearbite.data.remote.dto.SearchRestaurantsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {

    @GET("interpreter")
    suspend fun searchRestaurants(
        @Query("latitude")
        latitude: Double,

        @Query("longitude")
        longitude: Double,

        @Query("limit")
        limit: Int,

        @Query("offset")
        offset: Int
    ): SearchRestaurantsResponseDto

}
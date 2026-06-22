package com.geekstudio.nearbite.data.remote.api

import com.geekstudio.nearbite.data.remote.dto.OverpassResponseDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OverpassApi {

    @Headers(
        "Accept: application/json",
        "Content-Type: text/plain"
    )
    @POST("interpreter")
    suspend fun queryRestaurants(
        @Body query: RequestBody
    ): OverpassResponseDto
}
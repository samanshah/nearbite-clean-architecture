package com.geekstudio.nearbite.data.remote.datasource

import android.util.Log
import com.geekstudio.nearbite.data.mapper.toRestaurantOrNull
import com.geekstudio.nearbite.data.remote.api.OverpassApi
import com.geekstudio.nearbite.data.remote.query.OverpassQueryBuilder
import com.geekstudio.nearbite.domain.model.Restaurant
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class OverpassRestaurantRemoteDataSource @Inject constructor(
    private val api: OverpassApi
) : RestaurantRemoteDataSource {

    override suspend fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): List<Restaurant> {
        Log.d("NearBiteDataSource", "Overpass source called")

        val query = OverpassQueryBuilder.nearbyRestaurants(
            latitude = latitude,
            longitude = longitude
        )

        Log.d("NearBiteDataSource", "Overpass query:\n$query")

        val requestBody = query.toRequestBody(
            "text/plain".toMediaType()
        )

        val response = api.queryRestaurants(requestBody)

        val restaurants = response.elements
            .mapNotNull { it.toRestaurantOrNull() }
            .distinctBy { it.id }

        Log.d(
            "NearBiteDataSource",
            "Overpass result count = ${restaurants.size}"
        )

        return restaurants
    }
}
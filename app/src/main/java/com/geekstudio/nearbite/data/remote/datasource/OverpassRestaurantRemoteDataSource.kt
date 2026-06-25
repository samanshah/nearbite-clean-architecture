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
        longitude: Double,
        query: String
    ): List<Restaurant> {
        Log.d("NearBiteDataSource", "Overpass source called with query = $query")

        val overpassQuery = OverpassQueryBuilder.nearbyRestaurants(
            latitude = latitude,
            longitude = longitude
        )

        val requestBody = overpassQuery.toRequestBody(
            "text/plain".toMediaType()
        )

        val restaurants = api.queryRestaurants(requestBody)
            .elements
            .mapNotNull { element ->
                element.toRestaurantOrNull()
            }
            .distinctBy { restaurant ->
                restaurant.id
            }

        if (query.isBlank()) {
            return restaurants
        }

        return restaurants.filter { restaurant ->
            restaurant.title.contains(query, ignoreCase = true) ||
                    restaurant.category.contains(query, ignoreCase = true) ||
                    restaurant.address.contains(query, ignoreCase = true)
        }
    }
}
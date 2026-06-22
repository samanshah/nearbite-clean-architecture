package com.geekstudio.nearbite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geekstudio.nearbite.data.paging.OverpassRestaurantPagingSource
import com.geekstudio.nearbite.data.remote.api.OverpassApi
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: OverpassApi
) : RestaurantRepository {

    override fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<Restaurant>> {
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                OverpassRestaurantPagingSource(
                    api = api,
                    latitude = latitude,
                    longitude = longitude
                )
            }
        ).flow
    }
}
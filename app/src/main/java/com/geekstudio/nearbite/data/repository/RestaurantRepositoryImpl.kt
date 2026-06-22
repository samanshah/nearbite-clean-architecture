package com.geekstudio.nearbite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geekstudio.nearbite.data.paging.RestaurantPagingSource
import com.geekstudio.nearbite.data.remote.datasource.RestaurantRemoteDataSource
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val remoteDataSource: RestaurantRemoteDataSource
) : RestaurantRepository {

    override fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<Restaurant>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                RestaurantPagingSource(
                    remoteDataSource = remoteDataSource,
                    latitude = latitude,
                    longitude = longitude
                )
            }
        ).flow
    }
}
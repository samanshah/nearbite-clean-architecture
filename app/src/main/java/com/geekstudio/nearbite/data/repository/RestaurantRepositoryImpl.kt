package com.geekstudio.nearbite.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.geekstudio.nearbite.core.common.Result
import com.geekstudio.nearbite.data.local.dao.RestaurantDao
import com.geekstudio.nearbite.data.mapper.toDomain
import com.geekstudio.nearbite.data.paging.RestaurantPagingSource
import com.geekstudio.nearbite.data.remote.api.RestaurantApi
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val dao: RestaurantDao
) : RestaurantRepository {

    override fun getNearbyRestaurants(
        latitude: Double, longitude: Double
    ): Flow<PagingData<Restaurant>> {

        return Pager(

            config = PagingConfig(
                pageSize = 20
            ),

            pagingSourceFactory = {

                RestaurantPagingSource(
                    api, latitude, longitude
                )

            }

        ).flow

    }

    override suspend fun getRestaurantDetail(
        id: String
    ): Result<Restaurant> {

        return Result.Error(
            "Not implemented yet"
        )

    }

    override suspend fun toggleFavorite(
        restaurantId: String
    ) {

        dao.toggleFavorite(
            restaurantId
        )

    }

    override fun getFavoriteRestaurants(): Flow<List<Restaurant>> {

        return dao.observeFavorites().map { entities ->

                entities.map {
                    it.toDomain()
                }

            }

    }

}
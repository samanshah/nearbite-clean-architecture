package com.geekstudio.nearbite.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.geekstudio.nearbite.core.common.Result
import com.geekstudio.nearbite.data.local.dao.RemoteKeysDao
import com.geekstudio.nearbite.data.local.dao.RestaurantDao
import com.geekstudio.nearbite.data.local.database.NearBiteDatabase
import com.geekstudio.nearbite.data.mapper.toDomain
import com.geekstudio.nearbite.data.paging.RestaurantPagingSource
import com.geekstudio.nearbite.data.paging.RestaurantRemoteMediator
import com.geekstudio.nearbite.data.remote.api.RestaurantApi
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val dao: RestaurantDao,
    private val database: NearBiteDatabase,
) : RestaurantRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): Flow<PagingData<Restaurant>> {

        return Pager(

            config = PagingConfig(
                pageSize = 20
            ),

            remoteMediator =
                RestaurantRemoteMediator(
                    api = api,
                    database = database,
                    latitude = latitude,
                    longitude = longitude
                ),

            pagingSourceFactory = {

                dao.pagingSource()

            }

        ).flow.map { pagingData ->

            pagingData.map {

                it.toDomain()

            }

        }

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
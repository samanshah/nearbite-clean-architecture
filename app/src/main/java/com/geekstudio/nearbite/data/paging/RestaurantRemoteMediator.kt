package com.geekstudio.nearbite.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.geekstudio.nearbite.data.local.database.NearBiteDatabase
import com.geekstudio.nearbite.data.local.entity.RemoteKeysEntity
import com.geekstudio.nearbite.data.local.entity.RestaurantEntity
import com.geekstudio.nearbite.data.mapper.toEntity
import com.geekstudio.nearbite.data.remote.api.RestaurantApi

@OptIn(
    ExperimentalPagingApi::class
)
class RestaurantRemoteMediator(

    private val api: RestaurantApi,

    private val database: NearBiteDatabase,

    private val latitude: Double,

    private val longitude: Double

) : RemoteMediator<Int, RestaurantEntity>() {

    override suspend fun load(

        loadType: LoadType,

        state: PagingState<Int, RestaurantEntity>

    ): MediatorResult {

        return try {

            val page = when (loadType) {

                LoadType.REFRESH -> 0

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {

                    val lastItem = state.lastItemOrNull()

                    val remoteKeys = lastItem?.id?.let {

                        database.remoteKeysDao().remoteKeysById(it)

                    }

                    remoteKeys?.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                }

            }

            val response = api.searchRestaurants(
                latitude = latitude,
                longitude = longitude,
                limit = state.config.pageSize,
                offset = page
            )

            database.withTransaction {

                if (loadType == LoadType.REFRESH) {

                    database.restaurantDao().clearAll()

                    database.remoteKeysDao().clearRemoteKeys()

                }

                val keys = response.restaurants.map {

                    RemoteKeysEntity(

                        restaurantId = it.id,

                        prevKey = if (page == 0) null
                        else page - 20,

                        nextKey = if (response.restaurants.isEmpty()) null
                        else page + 20

                    )

                }

                database.remoteKeysDao().insertAll(keys)

                database.restaurantDao().insertAll(

                        response.restaurants.map {
                            it.toEntity()
                        }

                    )

            }

            MediatorResult.Success(

                endOfPaginationReached = response.restaurants.isEmpty()

            )

        } catch (e: Exception) {

            MediatorResult.Error(e)

        }

    }

}
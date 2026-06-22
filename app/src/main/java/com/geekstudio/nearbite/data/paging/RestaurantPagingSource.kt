package com.geekstudio.nearbite.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geekstudio.nearbite.data.remote.datasource.RestaurantRemoteDataSource
import com.geekstudio.nearbite.domain.model.Restaurant

class RestaurantPagingSource(
    private val remoteDataSource: RestaurantRemoteDataSource,
    private val latitude: Double,
    private val longitude: Double
) : PagingSource<Int, Restaurant>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Restaurant> {
        return try {
            val restaurants = remoteDataSource.getNearbyRestaurants(
                latitude = latitude,
                longitude = longitude
            )

            LoadResult.Page(
                data = restaurants,
                prevKey = null,
                nextKey = null
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Restaurant>
    ): Int? {
        return null
    }
}
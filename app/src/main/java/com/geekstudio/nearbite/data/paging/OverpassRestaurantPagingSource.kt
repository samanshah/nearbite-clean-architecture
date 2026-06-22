package com.geekstudio.nearbite.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geekstudio.nearbite.data.mapper.toRestaurantOrNull
import com.geekstudio.nearbite.data.remote.api.OverpassApi
import com.geekstudio.nearbite.data.remote.query.OverpassQueryBuilder
import com.geekstudio.nearbite.domain.model.Restaurant
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException

class OverpassRestaurantPagingSource(
    private val api: OverpassApi,
    private val latitude: Double,
    private val longitude: Double
) : PagingSource<Int, Restaurant>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Restaurant> {
        return try {
            val query = OverpassQueryBuilder.nearbyRestaurants(
                latitude = latitude,
                longitude = longitude
            )

            Log.d(TAG, "Overpass query:\n$query")

            val requestBody = query.toRequestBody(
                "text/plain".toMediaType()
            )

            val response = api.queryRestaurants(
                query = requestBody
            )

            val restaurants = response.elements
                .mapNotNull { element ->
                    element.toRestaurantOrNull()
                }
                .distinctBy {
                    it.id
                }

            Log.d(TAG, "Loaded restaurants count: ${restaurants.size}")

            LoadResult.Page(
                data = restaurants,
                prevKey = null,
                nextKey = null
            )
        } catch (e: HttpException) {
            val errorBody = e.response()
                ?.errorBody()
                ?.string()

            Log.e(
                TAG,
                "HTTP error code=${e.code()}, body=$errorBody",
                e
            )

            LoadResult.Error(e)
        } catch (e: Exception) {
            Log.e(TAG, "Unknown paging error", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, Restaurant>
    ): Int? {
        return null
    }

    private companion object {
        const val TAG = "OverpassPaging"
    }
}
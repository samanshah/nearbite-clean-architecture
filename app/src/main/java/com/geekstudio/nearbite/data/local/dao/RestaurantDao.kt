package com.geekstudio.nearbite.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekstudio.nearbite.data.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Query(
        "SELECT * FROM restaurants"
    )
    fun pagingSource(): PagingSource<Int, RestaurantEntity>

    @Query(
        "SELECT * FROM restaurants WHERE isFavorite = 1"
    )
    fun observeFavorites(): Flow<List<RestaurantEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        restaurants: List<RestaurantEntity>
    )

    @Query(
        "DELETE FROM restaurants"
    )
    suspend fun clearAll()

    @Query(
        """
        UPDATE restaurants
        SET isFavorite =
            NOT isFavorite
        WHERE id = :restaurantId
        """
    )
    suspend fun toggleFavorite(
        restaurantId: String
    )

}
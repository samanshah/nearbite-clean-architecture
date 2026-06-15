package com.geekstudio.nearbite.data.local.dao

import androidx.room.*
import com.geekstudio.nearbite.data.local.entity.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertAll(
        keys: List<RemoteKeysEntity>
    )

    @Query(
        """
        SELECT * FROM remote_keys
        WHERE restaurantId = :id
        """
    )
    suspend fun remoteKeysById(
        id: String
    ): RemoteKeysEntity?

    @Query(
        "DELETE FROM remote_keys"
    )
    suspend fun clearRemoteKeys()

}
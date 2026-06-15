package com.geekstudio.nearbite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "remote_keys"
)
data class RemoteKeysEntity(

    @PrimaryKey
    val restaurantId: String,

    val prevKey: Int?,

    val nextKey: Int?

)
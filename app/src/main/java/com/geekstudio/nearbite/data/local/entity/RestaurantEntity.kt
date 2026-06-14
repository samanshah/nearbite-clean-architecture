package com.geekstudio.nearbite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "restaurants"
)
data class RestaurantEntity(

    @PrimaryKey val id: String,

    val name: String,

    val imageUrl: String,

    val rating: Double,

    val category: String,

    val latitude: Double,

    val longitude: Double,

    val address: String,

    val isFavorite: Boolean

)
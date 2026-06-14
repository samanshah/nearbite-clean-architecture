package com.geekstudio.nearbite.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geekstudio.nearbite.data.local.dao.RestaurantDao
import com.geekstudio.nearbite.data.local.entity.RestaurantEntity

@Database(
    entities = [RestaurantEntity::class], version = 2, exportSchema = false
)
abstract class NearBiteDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

}
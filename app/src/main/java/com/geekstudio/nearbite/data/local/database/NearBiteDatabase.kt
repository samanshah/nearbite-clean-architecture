package com.geekstudio.nearbite.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geekstudio.nearbite.data.local.dao.RemoteKeysDao
import com.geekstudio.nearbite.data.local.dao.RestaurantDao
import com.geekstudio.nearbite.data.local.entity.RemoteKeysEntity
import com.geekstudio.nearbite.data.local.entity.RestaurantEntity

@Database(
    entities = [
        RestaurantEntity::class,
        RemoteKeysEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class NearBiteDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    abstract fun remoteKeysDao(): RemoteKeysDao

}
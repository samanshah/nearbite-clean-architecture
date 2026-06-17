package com.geekstudio.nearbite.core.database

import android.content.Context
import androidx.room.Room
import com.geekstudio.nearbite.data.local.dao.RemoteKeysDao
import com.geekstudio.nearbite.data.local.dao.RestaurantDao
import com.geekstudio.nearbite.data.local.database.NearBiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NearBiteDatabase {

        return Room.databaseBuilder(
            context,
            NearBiteDatabase::class.java,
            "nearbite.db"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    @Singleton
    fun provideRestaurantDao(
        database: NearBiteDatabase
    ): RestaurantDao {

        return database.restaurantDao()

    }

    @Provides
    @Singleton
    fun provideRemoteKeysDao(
        database: NearBiteDatabase
    ): RemoteKeysDao {

        return database.remoteKeysDao()

    }

}
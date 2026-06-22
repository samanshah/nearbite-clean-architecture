package com.geekstudio.nearbite.di

import android.util.Log
import com.geekstudio.nearbite.core.config.AppConfig
import com.geekstudio.nearbite.core.config.DataSourceType
import com.geekstudio.nearbite.data.remote.datasource.DemoRestaurantRemoteDataSource
import com.geekstudio.nearbite.data.remote.datasource.OverpassRestaurantRemoteDataSource
import com.geekstudio.nearbite.data.remote.datasource.RestaurantRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRestaurantRemoteDataSource(
        demoDataSource: DemoRestaurantRemoteDataSource,
        overpassDataSource: OverpassRestaurantRemoteDataSource
    ): RestaurantRemoteDataSource {
        Log.d(
            "NearBiteDataSource",
            "Selected data source = ${AppConfig.dataSourceType}"
        )

        return when (AppConfig.dataSourceType) {
            DataSourceType.DEMO -> demoDataSource
            DataSourceType.OVERPASS -> overpassDataSource
        }
    }
}
package com.geekstudio.nearbite.di

import com.geekstudio.nearbite.data.remote.datasource.DemoRestaurantRemoteDataSource
import com.geekstudio.nearbite.data.remote.datasource.RestaurantRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRestaurantRemoteDataSource(
        impl: DemoRestaurantRemoteDataSource
    ): RestaurantRemoteDataSource
}
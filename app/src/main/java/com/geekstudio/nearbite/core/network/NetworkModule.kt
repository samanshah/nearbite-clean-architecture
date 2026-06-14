package com.geekstudio.nearbite.core.network

import com.geekstudio.nearbite.BuildConfig
import com.geekstudio.nearbite.data.remote.AuthInterceptor
import com.geekstudio.nearbite.data.remote.api.RestaurantApi
import kotlin.jvm.java
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        val json =
            Json {
                ignoreUnknownKeys = true
            }

        return Retrofit.Builder()
            .baseUrl(
                BuildConfig.BASE_URL
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        AuthInterceptor()
                    )
                    .build()
            )
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json"
                        .toMediaType()
                )
            )
            .build()

    }

    @Provides
    @Singleton
    fun provideRestaurantApi(
        retrofit: Retrofit
    ): RestaurantApi {

        return retrofit.create(
            RestaurantApi::class.java
        )

    }

}
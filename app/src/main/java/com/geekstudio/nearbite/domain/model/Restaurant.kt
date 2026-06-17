package com.geekstudio.nearbite.domain.model

data class Restaurant(

    val id: String,

    val title: String,

    val imageUrl: String,

    val rating: Double,

    val category: String,

    val latitude: Double,

    val longitude: Double,

    val address: String,

    val isFavorite: Boolean

)
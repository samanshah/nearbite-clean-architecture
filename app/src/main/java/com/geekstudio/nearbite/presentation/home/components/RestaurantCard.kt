package com.geekstudio.nearbite.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.geekstudio.nearbite.core.ui.theme.NearBiteTheme
import com.geekstudio.nearbite.domain.model.Restaurant

@Composable
fun RestaurantCard(
    restaurant: Restaurant, onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }) {

        Column {

            AsyncImage(
                model = restaurant.imageUrl, contentDescription = null
            )

            Text(
                text = restaurant.title
            )

            Text(
                text = restaurant.category
            )

        }

    }

}

@Preview
@Composable
fun PreviewRestaurantCard() {

    NearBiteTheme {

    }

}
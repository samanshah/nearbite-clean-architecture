package com.geekstudio.nearbite.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geekstudio.nearbite.domain.model.Restaurant

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            RestaurantImage(
                imageUrl = restaurant.imageUrl,
                title = restaurant.title
            )

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = restaurant.title,
                    style = MaterialTheme.typography.titleMedium
                )

                RestaurantInfoChips(
                    category = restaurant.category,
                    rating = restaurant.rating
                )

                if (restaurant.address.isNotBlank()) {
                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )

                    Text(
                        text = "📍 ${restaurant.address}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
private fun RestaurantCardPreview() {
    com.geekstudio.nearbite.core.ui.theme.NearBiteTheme {
        RestaurantCard(
            restaurant = Restaurant(
                id = "1",
                title = "NearBite Burger House",
                imageUrl = "",
                rating = 4.6,
                category = "Fast Food",
                latitude = 35.6892,
                longitude = 51.3890,
                address = "Valiasr Street, Tehran",
                isFavorite = false
            ),
            onClick = {}
        )
    }
}
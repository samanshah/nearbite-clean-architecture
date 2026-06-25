package com.geekstudio.nearbite.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geekstudio.nearbite.domain.model.Restaurant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantBottomSheet(
    restaurant: Restaurant,
    onDismiss: () -> Unit,
    onViewDetailsClick: (Restaurant) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RestaurantImage(
                imageUrl = restaurant.imageUrl,
                title = restaurant.title
            )

            Text(
                text = restaurant.title,
                style = MaterialTheme.typography.headlineSmall
            )

            RestaurantInfoChips(
                category = restaurant.category,
                rating = restaurant.rating
            )

            Text(
                text = "📍 ${restaurant.address}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onViewDetailsClick(restaurant)
                }
            ) {
                Text("View details")
            }
        }
    }
}
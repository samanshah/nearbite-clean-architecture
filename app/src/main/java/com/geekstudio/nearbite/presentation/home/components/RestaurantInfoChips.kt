package com.geekstudio.nearbite.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun RestaurantInfoChips(
    category: String,
    rating: Double
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (category.isNotBlank()) {
            AssistChip(
                onClick = {},
                label = {
                    Text(category)
                }
            )
        }

        if (rating > 0.0) {
            AssistChip(
                onClick = {},
                label = {
                    Text("⭐ ${rating.formatRating()}")
                }
            )
        }
    }
}

private fun Double.formatRating(): String {
    return "%.1f".format(this)
}
package com.geekstudio.nearbite.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun RestaurantCardPlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shimmer()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PlaceholderBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            PlaceholderBox(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
            )

            PlaceholderBox(
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(16.dp)
            )

            PlaceholderBox(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(14.dp)
            )
        }
    }
}

@Composable
private fun PlaceholderBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(
            color = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant,
            shape = RoundedCornerShape(12.dp)
        )
    )
}
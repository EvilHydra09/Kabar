package com.example.kabarubuntu.presentation.home.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme

@Composable
fun PageIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedDotWidth: Dp = 16.dp,
    dotHeight: Dp = 8.dp,
    unselectedDotWidth: Dp = 8.dp,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        repeat(totalDots) { index ->
            val width by animateDpAsState(
                targetValue = if (index == selectedIndex) selectedDotWidth else unselectedDotWidth
            )
            val shape = if (index == selectedIndex) RoundedCornerShape(4.dp) else CircleShape
            val color = if (index == selectedIndex) selectedColor else unselectedColor

            Box(
                modifier = Modifier
                    .size(width = width, height = dotHeight)
                    .clip(shape)
                    .background(color)
            )
        }
    }
}
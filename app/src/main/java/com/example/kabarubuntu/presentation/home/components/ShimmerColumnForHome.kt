package com.example.kabarubuntu.presentation.home.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.EmptyScreen
import com.example.kabarubuntu.presentation.common.ShimmerColumn
import com.example.kabarubuntu.presentation.common.ShimmerNewsItemCard

@Composable
fun ShimmerColumnForHome(
    contentPadding: PaddingValues = PaddingValues(all = 10.dp)
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding
    ) {
        item {
            ShimmerEffect()
        }
        items(count = 8) {
            ShimmerNewsItemCard()
        }
    }
}

@Composable
fun ShimmerEffect() {
    val gradient = listOf(
        Color.Gray.copy(alpha = 0.9f),
        Color.Gray.copy(alpha = 0.3f),
        Color.Gray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = ""
    )

    val brush = Brush.linearGradient(
        colors = gradient,
        start = Offset(10f, 10f),
        end = Offset(translateAnim.value, translateAnim.value)
    )

    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(brush = brush)
    )
}

@Preview
@Composable
private fun ShimmerColumnPreview() {
    ShimmerColumn()
}

package com.example.kabarubuntu.presentation.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kabarubuntu.presentation.Dimens
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme

@Composable
fun PageIndicator(
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
) {
    Row(modifier = Modifier.padding(Dimens.Small)) {
        repeat(pageSize) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color = if (it == selectedPage) selectedColor else unselectedColor)
                    .size(14.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PageIndicatorPreview() {
    KabarUbuntuTheme {
        PageIndicator(pageSize = 3, selectedPage = 1)
    }
}
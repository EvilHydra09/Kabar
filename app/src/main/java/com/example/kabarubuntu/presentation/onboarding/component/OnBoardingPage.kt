package com.example.kabarubuntu.presentation.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.kabarubuntu.presentation.Dimens
import com.example.kabarubuntu.ui.theme.KabarUbuntuTheme

@Composable
fun OnBoardingPage(page: Page, modifier: Modifier = Modifier) {


    Column {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(Dimens.Medium))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = Dimens.Medium),
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary

            )
        Spacer(modifier = Modifier.height(Dimens.Medium))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = Dimens.Medium),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun OnBoardingPagePreview() {
    KabarUbuntuTheme {
        OnBoardingPage(page = pages[0])
    }
}
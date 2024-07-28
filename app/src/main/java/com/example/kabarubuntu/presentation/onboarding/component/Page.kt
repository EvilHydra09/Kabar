package com.example.kabarubuntu.presentation.onboarding.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import com.example.kabarubuntu.R

@Immutable
data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page(
        title = "Breaking News at Your Fingertips",
        description = "Stay updated with the latest headlines and in-depth coverage.",
        image = R.drawable.page1
    ),
    Page(
        title = "Personalized News Feed",
        description = "Tailor your news experience to your interests and preferences.",
        image = R.drawable.page2
    ),
    Page(
        title = "Read Offline, Anytime",
        description = "Download articles to enjoy even when you're without internet access.",
        image = R.drawable.page3
    )

)
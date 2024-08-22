package com.example.kabarubuntu.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import com.example.kabarubuntu.domain.model.Article
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoScrollingHorizontalPager(
    firstTenArticle: List<Article>,
    onClick: (Article) -> Unit,
    pagerState: PagerState
) {
    LaunchedEffect(key1 = pagerState) {
        while (true) {
            yield() // To ensure other composable can run in the meantime
            delay(2000) // Wait for 2 seconds
            val nextPage = (pagerState.currentPage + 1) % firstTenArticle.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(state = pagerState,) { page ->
            firstTenArticle[page].let { item ->
                ArticlePage(article = item, onClick = { onClick(item) })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        PageIndicator(
            totalDots = firstTenArticle.size, // Total number of pages
            selectedIndex = pagerState.currentPage
        )
    }
}
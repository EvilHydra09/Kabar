package com.example.kabarubuntu.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.NewsItemCard


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleListHome(
    article: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
    onClick: (Article) -> Unit,
    lazyState: LazyListState = rememberLazyListState(),
    pagerState: PagerState,
    firstTenArticle: List<Article>,
    contentPadding: PaddingValues = PaddingValues(all = 10.dp)// Add this parameter
) {
    val handlePagingResult = handlePagingResultForHome(article = article)

    if (handlePagingResult) {
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = contentPadding, // Use the passed contentPadding
            modifier = modifier
        ) {
            item {
                Text(
                    text = "Top Headlines",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                )
            }
            item {
                AutoScrollingHorizontalPager(
                    firstTenArticle = firstTenArticle,
                    onClick = onClick,
                    pagerState = pagerState
                )
            }
            item {
                Text(
                    text = "Recommended",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                )
            }

            items(count = article.itemCount - 10) {
                article[it + 10]?.let { item ->
                    NewsItemCard(article = item, onClick = { onClick(item) })
                }
            }
        }
    }
}
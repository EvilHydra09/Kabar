package com.example.kabarubuntu.presentation.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator

import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.EmptyScreen
import com.example.kabarubuntu.presentation.common.NewsItemCard
import kotlin.math.truncate


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
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
    val pullToRefreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    if (handlePagingResult) {

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = {

                article.refresh() },
            state = pullToRefreshState,
            indicator = {
                Indicator(
                    modifier = Modifier.align(Alignment.TopCenter).padding(top = 100.dp),
                    isRefreshing = isRefreshing,
                    state = pullToRefreshState
                )
            }
            ,
        ) {

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
}

@Composable
fun handlePagingResultForHome(
    article: LazyPagingItems<Article>,
    modifier: Modifier = Modifier
): Boolean {


    val loadState = article.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerColumnForHome(
                contentPadding = PaddingValues(top = 120.dp, start = 10.dp, end = 10.dp),
            )
            false
        }

        error != null -> {
            EmptyScreen(
                onRetryClicked = { article.retry() }
            )
            false
        }

        else -> {
            true
        }
    }
}
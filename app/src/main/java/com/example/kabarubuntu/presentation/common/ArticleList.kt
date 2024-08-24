package com.example.kabarubuntu.presentation.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleList(
    article: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
    onClick: (Article) -> Unit,
    lazyState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(all = 10.dp)// Add this parameter
) {
    val handlePagingResult = handlePagingResult(article = article)

    if (handlePagingResult) {
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding, // Use the passed contentPadding
            modifier = modifier
        ) {

            items(count = article.itemCount) {
                article[it]?.let { item ->
                    NewsItemCard(article = item, onClick = { onClick(item) })
                }
            }
        }
    }
}

@Composable
fun ArticleList(
    articles: List<Article>,
    modifier: Modifier = Modifier,
    onClick: (Article) -> Unit,
    lazyState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(all = 10.dp)// Add this parameter
) {
        if (articles.isEmpty()) {
            EmptyScreen()
            return
        }
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = contentPadding, // Use the passed contentPadding
            modifier = modifier
        ) {
            items(count = articles.size) {
              val article = articles[it]
                    NewsItemCard(article = article, onClick = { onClick(article) })
                }
            }

}

@Composable
fun handlePagingResult(
    article: LazyPagingItems<Article>,
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
            ShimmerColumn()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        article.itemCount == 0 -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}



@Composable
fun ShimmerColumn(
    contentPadding: PaddingValues = PaddingValues(all = 10.dp)
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding
    ) {
        item {

        }
        items(count = 8) {
            ShimmerNewsItemCard()
        }
    }
}



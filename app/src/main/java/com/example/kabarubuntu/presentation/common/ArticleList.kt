package com.example.kabarubuntu.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article

@Composable
fun ArticleList(
    article: LazyPagingItems<Article>,
    modifier: Modifier = Modifier,
    onClick: (Article) -> Unit,
    lazyState : LazyListState
) {

    val handlePagingResult = handlePagingResult(article = article)

    if (handlePagingResult) {
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(all = 10.dp)
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
fun handlePagingResult(
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
            ShimmerColumn()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}


@Composable
fun ShimmerColumn(modifier: Modifier = Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(count = 8) {
            ShimmerNewsItemCard()
        }
    }
}

@Preview
@Composable
private fun ShimmerColumnPreview() {
    ShimmerColumn()
}

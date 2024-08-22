package com.example.kabarubuntu.presentation.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.SearchBar
import com.example.kabarubuntu.presentation.common.isScrollingUp
import com.example.kabarubuntu.presentation.home.components.ArticleListHome


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateToSearch: () -> Unit,
    article: LazyPagingItems<Article>,
    navigateToDetails: (Article) -> Unit
) {
    val firstTenArticles = article.itemSnapshotList.items.take(10)


    val pagerState = rememberPagerState(
        pageCount = {
            firstTenArticles.size
        }
    )

    val lazyListState = rememberLazyListState()
    var text by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ArticleListHome(
            article = article,
            onClick = { article ->
                navigateToDetails(article)
            },
            lazyState = lazyListState,
            contentPadding = PaddingValues(top = 120.dp, start = 10.dp, end = 10.dp),
            pagerState = pagerState,
            firstTenArticle = firstTenArticles
        )

        SearchBar(
            searchQuery = text,
            onSearchQueryChange = { text = it },
            extended = lazyListState.isScrollingUp(),
            onSearch = {

            },
            onClick = {
                navigateToSearch()
            },
            readOnly = true,
            focusRequester = remember {
                FocusRequester()
            }
        )
    }

}







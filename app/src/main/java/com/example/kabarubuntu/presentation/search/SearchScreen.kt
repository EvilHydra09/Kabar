package com.example.kabarubuntu.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.ArticleList
import com.example.kabarubuntu.presentation.common.SearchBar
import com.example.kabarubuntu.presentation.nvgraph.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetail: (Article) -> Unit,
) {
    // Create a FocusRequester instance
    val focusRequester = remember { FocusRequester() }

    // Request focus when the screen is first displayed
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { event(SearchEvent.UpdateQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) },
            readOnly = false,
            focusRequester = focusRequester
        )
        Spacer(modifier = Modifier.height(10.dp))

        state.article?.let {
            val article = it.collectAsLazyPagingItems()
            ArticleList(article = article, onClick = {
                navigateToDetail(it)
            }
            )
        }
    }


}
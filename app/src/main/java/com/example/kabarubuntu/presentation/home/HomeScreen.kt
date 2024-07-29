package com.example.kabarubuntu.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.ArticleList
import com.example.kabarubuntu.presentation.common.SearchBar
import com.example.kabarubuntu.presentation.common.isScrollingUp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    article: LazyPagingItems<Article>
) {
    Surface(tonalElevation = 5.dp) {

        val lazyListState = rememberLazyListState()

        Column(modifier = Modifier.fillMaxSize()) {
            SearchBar(
                searchQuery = TextFieldValue(""),
                onSearchQueryChange = {},
                extended = lazyListState.isScrollingUp())
            ArticleList(article = article, onClick = {}, lazyState = lazyListState)
        }
    }
}
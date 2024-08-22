package com.example.kabarubuntu.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.presentation.common.ArticleList
import com.example.kabarubuntu.presentation.nvgraph.Route

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()


    ) {

        Text(
            modifier = Modifier.padding(10.dp),
            text = "Bookmark",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        )
        ArticleList(
            articles = state.article,
            onClick = {
                navigateToDetails(it)
            }
        )
    }

}
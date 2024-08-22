package com.example.kabarubuntu.presentation.bookmark

import com.example.kabarubuntu.domain.model.Article

data class BookmarkState(
    val article : List<Article> = emptyList()
)

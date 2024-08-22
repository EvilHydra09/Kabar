package com.example.kabarubuntu.presentation.search

import androidx.paging.PagingData
import com.example.kabarubuntu.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState(
    val searchQuery: String = "",
    val article : Flow<PagingData<Article>>? = null
)

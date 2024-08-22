package com.example.kabarubuntu.presentation.detail

import com.example.kabarubuntu.domain.model.Article

sealed class DetailsEvent {

 data class UpsertArticle(val article: Article) : DetailsEvent()

 data object RemoveSideEffect : DetailsEvent()

 data class CheckIfBookmarked(val article: Article) : DetailsEvent()
}
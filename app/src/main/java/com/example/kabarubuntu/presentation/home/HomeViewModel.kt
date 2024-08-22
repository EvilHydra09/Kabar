package com.example.kabarubuntu.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kabarubuntu.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUseCases: NewsUseCases
):ViewModel() {

    val news = newsUseCases.getNews(
        source = listOf("bbc-news","abc-news")
    ).cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            news.collect { pagingData ->
                Log.d("HomeViewModel", "Received PagingData: ${pagingData}")
            }
        }
    }
}
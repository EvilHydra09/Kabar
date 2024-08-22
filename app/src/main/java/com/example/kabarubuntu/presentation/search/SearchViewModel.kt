package com.example.kabarubuntu.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kabarubuntu.domain.usecase.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state : State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when(event) {
            SearchEvent.SearchNews -> {
                searchNews()
            }
            is SearchEvent.UpdateQuery -> {
                _state.value = _state.value.copy(searchQuery = event.query)
            }
        }
    }

    private fun searchNews() {
        val article = newsUseCases.searchNews(
            query = state.value.searchQuery,
            source = listOf("bbc-news","cnn")
        ).cachedIn(viewModelScope)

        _state.value = state.value.copy(article = article)


    }

}
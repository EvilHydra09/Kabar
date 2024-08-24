package com.example.kabarubuntu.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.kabarubuntu.domain.usecase.news.SearchNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNews: SearchNews
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _query = MutableStateFlow("")

    init {
        observeQuery()
    }

    private fun observeQuery() {
        _query
            .debounce(1000L)
            .distinctUntilChanged()
            .onEach {
                searchNews()
            }.launchIn(viewModelScope)
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            SearchEvent.SearchNews -> {
                searchNews()
            }

            is SearchEvent.UpdateQuery -> {
                _state.value = _state.value.copy(searchQuery = event.query)
                _query.value = event.query
            }
        }
    }

    private fun searchNews() {
        val article = searchNews(
            query = state.value.searchQuery,
            source = listOf("bbc-news", "cnn")
        ).cachedIn(viewModelScope)

        _state.value = state.value.copy(article = article)


    }

}
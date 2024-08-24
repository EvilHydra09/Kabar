package com.example.kabarubuntu.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabarubuntu.domain.usecase.news.SelectArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
   private val selectArticles: SelectArticles
): ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state : State<BookmarkState> = _state

    init {
        getArticles()
    }


    private fun getArticles(){
        selectArticles().onEach {article ->
            _state.value = _state.value.copy(article = article)
        }.launchIn(viewModelScope)
    }

}
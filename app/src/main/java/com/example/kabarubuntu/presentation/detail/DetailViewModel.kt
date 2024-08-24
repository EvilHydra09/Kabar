package com.example.kabarubuntu.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.usecase.news.DeleteArticle
import com.example.kabarubuntu.domain.usecase.news.SelectArticle
import com.example.kabarubuntu.domain.usecase.news.UpsertArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val selectArticleUseCase: SelectArticle,
    private val deleteArticleUseCase: DeleteArticle,
    private val upsertArticleUseCase: UpsertArticle,
): ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
            private set

    private val _isBookmarked = mutableStateOf(false)
    val isBookmarked: State<Boolean> = _isBookmarked

    fun onEvent(event : DetailsEvent){
        when(event){
            DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
            is DetailsEvent.UpsertArticle -> {
                viewModelScope.launch {
                    val article = selectArticleUseCase(event.article.url)
                    if(article == null){
                        upsertArticle(event.article)
                    }
                    else{
                        deleteArticle(event.article)
                    }
                    _isBookmarked.value = article == null
                }

            }

            is DetailsEvent.CheckIfBookmarked -> {
                checkIfBookmarked(event.article)
            }
        }

    }

    private suspend fun deleteArticle(article: Article) {
        deleteArticleUseCase(article)
        sideEffect = "Article Deleted"
    }
    private fun checkIfBookmarked(article: Article) {
        viewModelScope.launch {
            _isBookmarked.value = selectArticleUseCase(article.url) != null
        }
    }

    private suspend fun upsertArticle(article: Article) {
        upsertArticleUseCase(article)
        sideEffect = "Article Saved"
    }

}
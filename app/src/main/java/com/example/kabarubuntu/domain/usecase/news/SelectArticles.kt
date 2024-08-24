package com.example.kabarubuntu.domain.usecase.news

import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SelectArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke() : Flow<List<Article>> {
        return newsRepository.getSavedArticles().map {
            it.reversed()
        }
    }

}
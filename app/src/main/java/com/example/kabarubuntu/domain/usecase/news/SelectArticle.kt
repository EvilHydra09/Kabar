package com.example.kabarubuntu.domain.usecase.news

import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.repository.NewsRepository
import javax.inject.Inject

class SelectArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.getArticles(url)
    }
}
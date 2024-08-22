package com.example.kabarubuntu.domain.usecase.news

import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.domain.model.Article

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticle(url)
    }
}
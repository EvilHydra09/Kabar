package com.example.kabarubuntu.domain.usecase.news

import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles().map {
            it.reversed()
        }
    }

}
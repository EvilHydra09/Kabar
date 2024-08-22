package com.example.kabarubuntu.domain.usecase.news

import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }

}
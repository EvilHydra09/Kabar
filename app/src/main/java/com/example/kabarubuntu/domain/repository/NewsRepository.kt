package com.example.kabarubuntu.domain.repository

import androidx.paging.PagingData
import com.example.kabarubuntu.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>) : Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>) : Flow<PagingData<Article>>

    suspend fun upsert(article: Article)

    suspend fun delete(article: Article)

    suspend fun getArticles(url:String) : Article?

    fun getSavedArticles() : Flow<List<Article>>



}
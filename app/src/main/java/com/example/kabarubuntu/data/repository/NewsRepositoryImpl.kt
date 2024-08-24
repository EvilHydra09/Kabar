package com.example.kabarubuntu.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kabarubuntu.data.local.NewsDao
import com.example.kabarubuntu.data.remote.NewsApi
import com.example.kabarubuntu.data.remote.NewsPagingSource
import com.example.kabarubuntu.data.remote.SearchNewsPagingSource
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override  fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    api = newsApi,
                    sources = sources.joinToString(",")
                )
            }
        ).flow


    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    searchQuery = searchQuery,
                    api = newsApi,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
    }

    override suspend fun upsert(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun delete(article: Article) {
       newsDao.delete(article)
    }

    override suspend fun getArticles(url: String): Article? {
       return newsDao.getArticle(url)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}
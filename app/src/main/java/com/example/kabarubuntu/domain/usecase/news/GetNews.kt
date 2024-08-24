package com.example.kabarubuntu.domain.usecase.news

import android.util.Log
import androidx.paging.PagingData
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(source: List<String>): Flow<PagingData<Article>> {

        val news = newsRepository.getNews(source)

        return news
    }

}
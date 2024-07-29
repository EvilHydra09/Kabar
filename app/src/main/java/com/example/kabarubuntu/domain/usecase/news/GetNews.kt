package com.example.kabarubuntu.domain.usecase.news

import androidx.paging.PagingData
import com.example.kabarubuntu.domain.model.Article
import com.example.kabarubuntu.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

     operator fun invoke(source : List<String>):Flow<PagingData<Article>>{
        return newsRepository.getNews(source)
    }

}
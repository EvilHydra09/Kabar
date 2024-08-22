package com.example.kabarubuntu.data.remote

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kabarubuntu.domain.model.Article
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SearchNewsPagingSource(
    private val api: NewsApi,
    private val sources: String,
    private val searchQuery: String,
) : PagingSource<Int, Article>() {
    private var totalCount = 0
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = api.searchNews(query = searchQuery, page = page, sources = sources)
            totalCount += newsResponse.articles.size
            val article = newsResponse.articles.distinctBy {
                it.title
            }.map {
                it.copy(
                    publishedAt = formatPublishedAt(it.publishedAt?: "")
                )
            }
            LoadResult.Page(
                data = article,
                nextKey = if(totalCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )


        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatPublishedAt(date: String): String {
        val originalFormat = DateTimeFormatter.ISO_DATE_TIME
        val desiredFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a") // Desired format
        val parsedDate = LocalDateTime.parse(date, originalFormat)
        return parsedDate.format(desiredFormat)
    }
}
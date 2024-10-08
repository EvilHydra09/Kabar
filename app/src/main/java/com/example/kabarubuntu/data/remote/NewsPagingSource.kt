package com.example.kabarubuntu.data.remote

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kabarubuntu.domain.model.Article
import okio.IOException
import retrofit2.HttpException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsPagingSource(
    private val api: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalCount = 0


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {

            val newsResponse = api.getNews(page = page, sources = sources)
            totalCount += newsResponse.articles.size


            val articles = newsResponse.articles.distinctBy { it.title }.map { article ->
                article.copy(publishedAt = formatPublishedAt(article.publishedAt ?: ""))
            }


            LoadResult.Page(
                data = articles,
                nextKey = if (totalCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )


        } catch (e: IOException) {
            LoadResult.Error(
                throwable = e
            )
        } catch (e: HttpException) {
            LoadResult.Error(
                throwable = e
            )
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun formatPublishedAt(date: String): String {
        val originalFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ISO_DATE_TIME
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val desiredFormat = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a") // Desired format
        val parsedDate = LocalDateTime.parse(date, originalFormat)
        return parsedDate.format(desiredFormat)
    }

}
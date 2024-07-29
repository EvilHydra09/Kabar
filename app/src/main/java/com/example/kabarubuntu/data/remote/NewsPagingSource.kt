package com.example.kabarubuntu.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kabarubuntu.domain.model.Article

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
            val article = newsResponse.articles.distinctBy {
                it.title
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


    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}
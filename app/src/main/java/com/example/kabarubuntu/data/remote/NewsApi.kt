package com.example.kabarubuntu.data.remote

import com.example.kabarubuntu.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page : Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey : String = "c95c07c7859d47e1af249255ddaac327"
    ) : NewsResponse


    @GET("everything")
    suspend fun searchNews(
        @Query("q") query : String,
        @Query("page") page : Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey : String = "c95c07c7859d47e1af249255ddaac327"
    ) : NewsResponse
}
package com.example.kabarubuntu.data.remote.dto

import com.example.kabarubuntu.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
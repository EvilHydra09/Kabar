package com.example.kabarubuntu.domain.usecase.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val deleteArticle: DeleteArticle,
    val selectArticles: SelectArticles,
    val upsertArticle: UpsertArticle,
    val selectArticle: SelectArticle
)

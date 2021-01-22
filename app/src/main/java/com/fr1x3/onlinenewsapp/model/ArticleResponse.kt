package com.fr1x3.onlinenewsapp.model

data class ArticleResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
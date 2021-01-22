package com.fr1x3.onlinenewsapp.repository

import com.fr1x3.onlinenewsapp.api.RetrofitInstance

class NewsRepository {


    // get all news articles
    suspend fun getAllNewsArticles(
        countryCode: String,
        pageNumber: Int
    ) =
        RetrofitInstance.newsApi.getAllNews(countryCode, pageNumber)


    // search for news article title
    suspend fun searchNewsArticle(
        searchParam: String,
        countryCode: String,
        pageNumber: Int
    ) = RetrofitInstance.newsApi.searchNewsItem(
        searchParam,
        countryCode,
        pageNumber
    )

}
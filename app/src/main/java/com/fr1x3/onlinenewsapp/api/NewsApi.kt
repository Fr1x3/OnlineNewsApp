package com.fr1x3.onlinenewsapp.api

import com.fr1x3.onlinenewsapp.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    // get all news
    @GET("v2/top-headlines")
    suspend fun getAllNews(
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en",
        @Query("apiKey") apikey: String = ""
    ) : Response<ArticleResponse>


    // search for specific news topic and/or category
    suspend fun searchNewsItem(
        @Query("qInTitle") searchParam: String,
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en"
    ) : Response<ArticleResponse>
}
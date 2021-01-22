package com.fr1x3.onlinenewsapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    // get all news
    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en"
    )


    // search for specific news topic and/or category
    suspend fun searchNewsItem(
        @Query("qInTitle") searchParam: String,
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en"
    )
}
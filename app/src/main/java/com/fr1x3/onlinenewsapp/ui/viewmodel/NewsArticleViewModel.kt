package com.fr1x3.onlinenewsapp.ui.viewmodel


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fr1x3.onlinenewsapp.NewsApplication
import com.fr1x3.onlinenewsapp.model.ArticleResponse
import com.fr1x3.onlinenewsapp.repository.NewsRepository
import com.fr1x3.onlinenewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsArticleViewModel(
        app: Application,
        val newsRepository: NewsRepository
): AndroidViewModel(app) {

    // variables declaration
    var countryCode: String = "us"

    val newsArticles: MutableLiveData<Resource<ArticleResponse>> = MutableLiveData()
    var allNewsPageNumber = 1
    var newsArticleResponse: ArticleResponse ?= null

    val searchNews : MutableLiveData<Resource<ArticleResponse>> = MutableLiveData()
    var searchNewsPageNumber = 1
    var searchNewsResponse: ArticleResponse ?= null

    init {
        // call to inital loading all news
        getAllArticles("us")
    }

    // get all news function
    fun getAllArticles( countryCode: String) = viewModelScope.launch {
        safeCallGetAllArticles()
    }
    // search news article function
    fun searchForArticles(
        searchParam: String,
        countryCode: String

    ) = viewModelScope.launch {
        safeCallSearchArticles(searchParam, countryCode)
    }

    // handle all articles response
    private fun handleAllNewsArticlesResponse(
        response: Response<ArticleResponse>
    ) : Resource<ArticleResponse>{
        if( response.isSuccessful){
            response.body()?.let {news ->
                return Resource.Success(news)
            }
        }
        return Resource.Error(response.message())
    }

    // handle search news response
    private fun handleSearchArticlesResponse(
        response: Response<ArticleResponse>
    ) : Resource<ArticleResponse>{
        if( response.isSuccessful){
            response.body()?.let {news ->
                return Resource.Success(news)
            }
        }
        return Resource.Error(response.message())
    }

    // safe all articles call
    private suspend fun safeCallGetAllArticles() {
        newsArticles.postValue(Resource.Loading())
        try {
            if( hasInternetConnection()){
                val response = newsRepository.getAllNewsArticles(countryCode, allNewsPageNumber)
                newsArticles.postValue(handleAllNewsArticlesResponse(response))
            }else
                newsArticles.postValue(Resource.Error("Device not connected to the Internet"))
        }catch (t: Throwable){
            when(t){
                is IOException -> newsArticles.postValue(Resource.Error("Device not connected to the Internet"))
                else -> newsArticles.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    // safe search news call
    private suspend fun safeCallSearchArticles(searchParam: String, countryCode: String) {
        newsArticles.postValue(Resource.Loading())
        try {
            if( hasInternetConnection()){
                val response = newsRepository.searchNewsArticle(searchParam,countryCode,searchNewsPageNumber)
                newsArticles.postValue(handleSearchArticlesResponse(response))
            }else
                newsArticles.postValue(Resource.Error("Device not connected to the Internet"))
        }catch (t: Throwable){
            when(t){
                is IOException -> newsArticles.postValue(Resource.Error("Device not connected to the Internet"))
                else -> newsArticles.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    // check internet connection
    fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when{
                capabilities.hasTransport(TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(TRANSPORT_ETHERNET ) -> return true
                else -> return false
            }
        }else{
            val activeNetwork = connectivityManager.activeNetworkInfo ?.run {
                return when(type){
                    TYPE_WIFI -> true
                    TYPE_ETHERNET -> true
                    TYPE_MOBILE -> true
                    else -> false
            }

            }
        }
        return false
    }

}
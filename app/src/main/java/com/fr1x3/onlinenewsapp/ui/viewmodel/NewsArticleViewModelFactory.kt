package com.fr1x3.onlinenewsapp.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fr1x3.onlinenewsapp.NewsApplication
import com.fr1x3.onlinenewsapp.repository.NewsRepository

class NewsArticleViewModelFactory (
    val app: Application,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsArticleViewModel(app, newsRepository) as T
    }
}
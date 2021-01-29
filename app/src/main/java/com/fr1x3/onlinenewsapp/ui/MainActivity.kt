package com.fr1x3.onlinenewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fr1x3.onlinenewsapp.NewsApplication
import com.fr1x3.onlinenewsapp.R
import com.fr1x3.onlinenewsapp.databinding.ActivityMainBinding
import com.fr1x3.onlinenewsapp.repository.NewsRepository
import com.fr1x3.onlinenewsapp.ui.viewmodel.NewsArticleViewModel
import com.fr1x3.onlinenewsapp.ui.viewmodel.NewsArticleViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var activityBinding: ActivityMainBinding
    lateinit var viewModel: NewsArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = NewsRepository()
        val viewModelFactory = NewsArticleViewModelFactory(application , repository)
         viewModel = ViewModelProvider(this, viewModelFactory).get(NewsArticleViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        activityBinding.bottomNavigationView.setupWithNavController(navController)


        // remove bottom navigation on navigation to news item
//        navController.addOnDestinationChangedListener{ _, destination, _ ->
//            if ( destination.id == )
//        }
    }
}
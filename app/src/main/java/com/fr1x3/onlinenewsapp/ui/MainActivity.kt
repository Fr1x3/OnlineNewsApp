package com.fr1x3.onlinenewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fr1x3.onlinenewsapp.R
import com.fr1x3.onlinenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment!!.findNavController()
        activityBinding.bottomNavigationView.setupWithNavController(navController)


        // remove bottom navigation on navigation to news item
//        navController.addOnDestinationChangedListener{ _, destination, _ ->
//            if ( destination.id == )
//        }
    }
}
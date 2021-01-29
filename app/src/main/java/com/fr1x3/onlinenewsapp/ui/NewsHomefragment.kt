package com.fr1x3.onlinenewsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fr1x3.onlinenewsapp.NewsApplication
import com.fr1x3.onlinenewsapp.R
import com.fr1x3.onlinenewsapp.adapters.NewsAdapter
import com.fr1x3.onlinenewsapp.databinding.FragmentHomeBinding
import com.fr1x3.onlinenewsapp.repository.NewsRepository
import com.fr1x3.onlinenewsapp.ui.viewmodel.NewsArticleViewModel
import com.fr1x3.onlinenewsapp.ui.viewmodel.NewsArticleViewModelFactory
import com.fr1x3.onlinenewsapp.util.Resource

class NewsHomefragment: Fragment() {


    private val TAG = "NewsHome Fragament"
    private var _binding: FragmentHomeBinding?= null
    private val binding
        get() = _binding!!

    lateinit var viewModel: NewsArticleViewModel
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ( activity as MainActivity).viewModel

        setUpRecyclerView()

        viewModel.newsArticles.observe( viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    Log.d( TAG, " Successful  response")
                    hideProgressBar()
                    response?.data.let{article ->
                        adapter.differ.submitList(article?.articles!!.toList())

                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response?.let { message ->
                        Log.d(TAG, "An error occured $message")
                        Toast.makeText( requireContext(), message.toString(), Toast.LENGTH_LONG).show()
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                    Log.d(TAG, "data loading")
                }
            }

        })
    }

    private fun showProgressBar(){
        binding.progressBar?.let {
            it.visibility = View.VISIBLE
        }
    }

    private fun hideProgressBar(){
        binding.progressBar?.visibility = View.GONE
    }
    private fun setUpRecyclerView(){
        adapter = NewsAdapter()
        binding.rvHomeNews?.let{
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
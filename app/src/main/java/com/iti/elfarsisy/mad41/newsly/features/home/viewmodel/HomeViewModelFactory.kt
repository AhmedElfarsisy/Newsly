package com.iti.elfarsisy.mad41.newsly.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iti.elfarsisy.mad41.newsly.data.repository.NewsRepository

class HomeViewModelFactory (private  val newsRepository: NewsRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        HomeViewModel(newsRepository) as T
}
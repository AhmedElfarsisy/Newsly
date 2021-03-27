package com.iti.elfarsisy.mad41.newsly.features.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem

class ArticleDetailsViewModelFactory (private val article:ArticlesItem): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        ArticleDetailsViewModel(article) as T
}
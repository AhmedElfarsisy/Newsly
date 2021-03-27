package com.iti.elfarsisy.mad41.newsly.features.details.viewmodel

import androidx.lifecycle.ViewModel
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import timber.log.Timber

class ArticleDetailsViewModel(article: ArticlesItem) : ViewModel() {
    init {
        Timber.i("$article")
    }

}
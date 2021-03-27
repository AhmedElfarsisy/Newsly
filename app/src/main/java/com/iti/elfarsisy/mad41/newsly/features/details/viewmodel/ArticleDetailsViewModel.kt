package com.iti.elfarsisy.mad41.newsly.features.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iti.elfarsisy.mad41.myapplication.data.source.remote.NetworkState
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import com.iti.elfarsisy.mad41.newsly.data.model.NewsResponse
import timber.log.Timber

class ArticleDetailsViewModel(article: ArticlesItem) : ViewModel() {
     val articleLiveData = MutableLiveData<ArticlesItem>()


    init {
        articleLiveData.postValue(article)
    }

}
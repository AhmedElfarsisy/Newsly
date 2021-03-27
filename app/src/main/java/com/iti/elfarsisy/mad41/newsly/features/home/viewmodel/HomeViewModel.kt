package com.iti.elfarsisy.mad41.newsly.features.home.viewmodel

import androidx.lifecycle.*
import com.iti.elfarsisy.mad41.myapplication.data.source.remote.NetworkState
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import com.iti.elfarsisy.mad41.newsly.data.model.NewsResponse
import com.iti.elfarsisy.mad41.newsly.data.repository.NewsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import android.util.Pair
import android.view.View
import androidx.navigation.Navigation
import com.iti.elfarsisy.mad41.newsly.features.home.ui.HomeFragmentDirections

class HomeViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    private val _newsResponseLive = MediatorLiveData<NewsResponse>()
    val newsResponseLive: MutableLiveData<NewsResponse> = _newsResponseLive
    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState
    var navigationLiveData: MutableLiveData<Pair<Boolean, ArticlesItem>?> = MutableLiveData()

    init {
        Timber.i("Home ViewModel init")
        navigationLiveData.value = Pair.create(false, null)
        updateNetworkState(NetworkState.LOADING)
        fetchNewsData()
    }

    private fun fetchNewsData() {
        viewModelScope.launch {
            val response = async {
                newsRepository.fetchWeatherAlerts(
                    "tesla",
                    "b314a9c320c44b53a3555296bc23cb46"
                )
            }
            response.await()?.let { response ->
                if (response.isSuccessful) {
                    _newsResponseLive.postValue(response.body())
                    Timber.i(response.body()?.articles.toString())
                    updateNetworkState(NetworkState.DONE)
                } else {
                    updateNetworkState(NetworkState.ERROR)
                    Timber.i("${response.message()}")
                }
            }
        }
    }

    private fun updateNetworkState(state: NetworkState) {
        _networkState.postValue(state)
    }

    fun navigateToDetails(article: ArticlesItem, view: View) {
        article?.let {
            val action =
                HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
    }

    fun addToFavorite(articleId: ArticlesItem) {
    }
}
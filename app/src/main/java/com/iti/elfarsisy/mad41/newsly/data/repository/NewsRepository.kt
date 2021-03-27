package com.iti.elfarsisy.mad41.newsly.data.repository

import android.content.Context
import com.iti.elfarsisy.mad41.newsly.data.datasource.remote.NewsApi
import com.iti.elfarsisy.mad41.newsly.data.model.NewsResponse
import retrofit2.Response

class NewsRepository(private val context: Context) {
    suspend fun fetchWeatherAlerts(
        searchWord: String,
        apiKey: String
    ): Response<NewsResponse> {
        return NewsApi.getNewsRetrofitClient()
            .fetchNewsData(searchWord = searchWord, apiKey = apiKey)
    }
}
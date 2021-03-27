package com.iti.elfarsisy.mad41.newsly.data.datasource.remote

import com.iti.elfarsisy.mad41.newsly.data.model.NewsResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

//?q=tesla&from=2021-02-27&sortBy=publishedAt&
// apiKey=b314a9c320c44b53a3555296bc23cb46

object NewsApi {
    private const val BASE_URL = "https://newsapi.org/v2/"
    var okHttpClient = OkHttpClient().newBuilder()
        .connectTimeout(25, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    fun getNewsRetrofitClient() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(NewsApiServices::class.java)
}


interface NewsApiServices {
    @GET("everything")
    suspend fun fetchNewsData(
        @Query("q") searchWord: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsResponse>

}
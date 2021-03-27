package com.iti.elfarsisy.mad41.newsly.features.home.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import com.iti.elfarsisy.mad41.newsly.features.home.viewmodel.HomeViewModel

object HomeBindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["viewModel", "newsDataset"], requireAll = true)
    fun bindHomeAdapter(
        recyclerView: RecyclerView,
        homeViewModel: HomeViewModel,
        articlesItemList: List<ArticlesItem>?
    ) {
        if (recyclerView.adapter == null) {
            val articleAdapter = HomeNewsAdapter()
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = articleAdapter
            articleAdapter.setOnAlrticleClickListener(object:OnArticleClickListener{
                override fun onClickArticle(article: ArticlesItem) {
                    homeViewModel.navigateToDetails(article,recyclerView)
                }

                override fun onClickFavorite(article: ArticlesItem) {
                    homeViewModel.addToFavorite(article)
                }
            })


        }
        (recyclerView.adapter as HomeNewsAdapter?)?.submitList(articlesItemList)
    }

}


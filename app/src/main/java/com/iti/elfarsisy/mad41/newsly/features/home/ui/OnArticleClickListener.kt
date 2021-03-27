package com.iti.elfarsisy.mad41.newsly.features.home.ui

import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem

interface OnArticleClickListener {
    fun onClickArticle(article: ArticlesItem)
    fun onClickFavorite(article: ArticlesItem)
}
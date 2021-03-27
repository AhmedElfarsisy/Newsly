package com.iti.elfarsisy.mad41.newsly.features.home.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.iti.elfarsisy.mad41.newsly.R
import com.iti.elfarsisy.mad41.newsly.data.model.ArticlesItem
import com.iti.elfarsisy.mad41.newsly.databinding.HomeNewsItemBinding

class HomeNewsAdapter : ListAdapter<ArticlesItem, HomeNewsAdapter.HomeNewsHolder>(DiffCallBack) {
    lateinit var onArticleClickListener: OnArticleClickListener

    fun setOnAlrticleClickListener(onArticleClickListener: OnArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener
    }

    class HomeNewsHolder(private val binding: HomeNewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var likeImageView: ImageView = binding.likeImage

        fun bind(item: ArticlesItem?) {
            binding.mArticlesItem = item
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsHolder {
        return HomeNewsHolder(
            HomeNewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeNewsHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onArticleClickListener.onClickArticle(item)
        }
        holder.likeImageView.setOnClickListener {
            it.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
            onArticleClickListener.onClickFavorite(item)
        }

    }

    companion object DiffCallBack : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem) =
            oldItem.articleId == newItem.articleId
    }

}
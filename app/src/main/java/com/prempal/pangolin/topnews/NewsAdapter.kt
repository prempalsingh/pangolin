package com.prempal.pangolin.topnews

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.data.remote.model.Article

class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.NewsItemViewHolder>(ARTICLE_COMPARATOR) {

    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(NewsItemView(parent.context))
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = getItem(position)
        holder.bindArticle(article!!)
    }

    class NewsItemViewHolder(private val newsItemView: NewsItemView) :
        RecyclerView.ViewHolder(newsItemView) {

        fun bindArticle(article: Article) {
            newsItemView.bindArticle(article)
        }
    }
}

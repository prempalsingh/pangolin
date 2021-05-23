package com.prempal.pangolin.topnews

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.data.remote.model.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {

    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        return NewsItemViewHolder(NewsItemView(parent.context))
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = articles[position]
        holder.bindArticle(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class NewsItemViewHolder(private val newsItemView: NewsItemView) :
        RecyclerView.ViewHolder(newsItemView) {

        fun bindArticle(article: Article) {
            newsItemView.bindArticle(article)
        }
    }
}

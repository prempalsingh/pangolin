package com.prempal.pangolin.ui.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.prempal.pangolin.data.Article
import com.prempal.pangolin.ui.MainViewModel
import javax.inject.Inject

/**
 * Created by prempal on 2020-02-09.
 */
class HeadlineAdapter @Inject constructor(private val viewModel: MainViewModel) :
    ListAdapter<Article, HeadlineViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val viewHolder = HeadlineViewHolder.from(parent)
        viewHolder.itemView.setOnClickListener {
            viewModel.onHeadlineClick(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

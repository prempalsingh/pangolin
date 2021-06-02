package com.prempal.pangolin.topnews

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class NewsLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<NewsLoadStateAdapter.LoadStateItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateItemViewHolder {
        return LoadStateItemViewHolder(NewsLoadStateItemView(parent.context, retry))
    }

    override fun onBindViewHolder(
        holder: LoadStateItemViewHolder,
        loadState: LoadState
    ) {
        holder.bindLoadState(loadState)
    }

    class LoadStateItemViewHolder(private val newsLoadStateItemView: NewsLoadStateItemView) :
        RecyclerView.ViewHolder(newsLoadStateItemView) {

        fun bindLoadState(loadState: LoadState) {
            newsLoadStateItemView.bind(loadState)
        }
    }
}

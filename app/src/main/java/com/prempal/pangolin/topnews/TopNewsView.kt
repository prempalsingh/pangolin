package com.prempal.pangolin.topnews

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.R
import com.prempal.pangolin.data.remote.model.Article
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
class TopNewsView(
    context: Context
) : ContourLayout(context) {

    private val progressBar = ProgressBar(context)
    private val errorButton = Button(context).apply {
        text = context.getString(R.string.retry)
    }
    private val topNewsAdapter = NewsAdapter()
    private val topNewsListView = RecyclerView(context).apply {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = topNewsAdapter
            .withLoadStateFooter(NewsLoadStateAdapter { topNewsAdapter.retry() })
    }

    init {
        progressBar.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )
        topNewsListView.layoutBy(
            x = matchParentX(),
            y = matchParentY()
        )
        errorButton.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )

        errorButton.setOnClickListener {
            topNewsAdapter.retry()
        }

        topNewsAdapter.addLoadStateListener {
            topNewsListView.isVisible = it.source.refresh is LoadState.NotLoading
            progressBar.isVisible = it.source.refresh is LoadState.Loading
            errorButton.isVisible = it.source.refresh is LoadState.Error
        }

        topNewsAdapter.onItemClickListener = {
            findNavController().navigate(TopNewsFragmentDirections.actionTopDestToArticleDest(it))
        }
    }

    fun setPagingData(lifecycle: Lifecycle, pagingData: PagingData<Article>) {
        topNewsAdapter.submitData(lifecycle, pagingData)
    }
}

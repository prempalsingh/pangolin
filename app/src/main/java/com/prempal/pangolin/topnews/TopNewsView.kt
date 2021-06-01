package com.prempal.pangolin.topnews

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.R
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
class TopNewsView(context: Context, onRetryClick: () -> Unit) : ContourLayout(context) {

    private val progressBar = ProgressBar(context)
    private val errorButton = Button(context).apply {
        text = context.getString(R.string.retry)
    }
    private val topNewsAdapter = NewsAdapter()
    private val topNewsListView = RecyclerView(context).apply {
        layoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter = topNewsAdapter
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
            onRetryClick()
        }
    }

    fun setLayoutState(state: TopNewsViewState) {
        progressBar.isVisible = state is TopNewsViewState.Loading
        errorButton.isVisible = state is TopNewsViewState.Error
        topNewsListView.isVisible = state is TopNewsViewState.Success

        if (state is TopNewsViewState.Success) {
            topNewsAdapter.articles = state.articles
        }
    }
}

package com.prempal.pangolin.topnews

import android.content.Context
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prempal.pangolin.R
import com.prempal.pangolin.utils.visibleIf
import com.squareup.contour.ContourLayout

class TopNewsView(context: Context) : ContourLayout(context) {

    private val progressBar = ProgressBar(context)

    // TODO: 24/05/21 Handle click of button
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
    }

    fun setLayoutState(state: TopNewsViewState) {
        progressBar.visibleIf(state is TopNewsViewState.Loading)
        errorButton.visibleIf(state is TopNewsViewState.Error)
        topNewsListView.visibleIf(state is TopNewsViewState.Success)

        if (state is TopNewsViewState.Success) {
            topNewsAdapter.articles = state.articles
        }
    }
}

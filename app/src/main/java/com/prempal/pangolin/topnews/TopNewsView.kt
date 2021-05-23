package com.prempal.pangolin.topnews

import android.content.Context
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.prempal.pangolin.R
import com.prempal.pangolin.utils.visibleIf
import com.squareup.contour.ContourLayout

class TopNewsView(context: Context) : ContourLayout(context) {

    private val progressBar = ProgressBar(context)
    private val errorButton = Button(context).apply {
        text = context.getString(R.string.retry)
    }

    // TODO: 24/05/21 replace with RecyclerView
    private val successTextView = TextView(context).apply {
        text = "News Loaded"
    }

    init {
        progressBar.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )
        successTextView.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )
        errorButton.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )
    }

    fun setLayoutState(state: TopNewsViewState) {
        progressBar.visibleIf(state is TopNewsViewState.Loading)
        errorButton.visibleIf(state is TopNewsViewState.Error)
        successTextView.visibleIf(state is TopNewsViewState.Success)
    }
}

package com.prempal.pangolin.topnews

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.prempal.pangolin.R
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
class NewsLoadStateItemView(context: Context, retry: () -> Unit) : ContourLayout(context) {
    private val progressBar = ProgressBar(context)
    private val errorText = TextView(context).apply {
        text = context.getString(R.string.error_msg)
    }
    private val retryButton = Button(context).apply {
        text = context.getString(R.string.retry)
        setOnClickListener {
            retry()
        }
    }

    init {
        contourHeightWrapContent()
        progressBar.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = topTo { parent.top() }
        )
        errorText.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = topTo { parent.top() }
        )
        retryButton.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = topTo { errorText.bottom() }
        )
    }

    fun bind(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        errorText.isVisible = loadState is LoadState.Error
        retryButton.isVisible = loadState is LoadState.Error
    }
}

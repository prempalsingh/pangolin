package com.prempal.pangolin.topnews

import android.content.Context
import android.widget.TextView
import com.prempal.pangolin.data.remote.model.Article
import com.squareup.contour.ContourLayout

class NewsItemView(context: Context) : ContourLayout(context) {
    private val title = TextView(context)

    init {
        contourHeightWrapContent()
        title.layoutBy(
            x = matchParentX(),
            y = topTo { parent.top() }
        )
    }

    fun bindArticle(article: Article) {
        title.text = article.title
    }
}

package com.prempal.pangolin.article

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import com.prempal.pangolin.data.remote.model.Article
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
class ArticleView(context: Context, article: Article) : ContourLayout(context) {

    private val webView = WebView(context).apply {
        webViewClient = WebViewClient()
        loadUrl(article.url)
    }

    init {
        webView.layoutBy(
            x = matchParentX(),
            y = matchParentY()
        )
    }
}

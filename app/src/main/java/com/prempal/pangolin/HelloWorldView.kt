package com.prempal.pangolin

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.squareup.contour.ContourLayout

@SuppressLint("ViewConstructor")
class HelloWorldView(context: Context, text: String = "Hello World") : ContourLayout(context) {
    private val textView = TextView(context).apply {
        this.text = text
        textSize = 18f
    }

    init {
        textView.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )
    }
}

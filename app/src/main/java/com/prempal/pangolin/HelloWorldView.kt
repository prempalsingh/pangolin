package com.prempal.pangolin

import android.content.Context
import android.widget.TextView
import com.squareup.contour.ContourLayout

class HelloWorldView(context: Context) : ContourLayout(context) {
    private val textView = TextView(context).apply {
        text = "Hello World"
        textSize = 18f
    }

    init {
        textView.layoutBy(
            x = centerHorizontallyTo { parent.centerX() },
            y = centerVerticallyTo { parent.centerY() }
        )
    }
}

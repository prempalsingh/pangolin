package com.prempal.pangolin.ui.rv

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.prempal.pangolin.R
import com.prempal.pangolin.data.Article

/**
 * Created by prempal on 2020-02-09.
 */
class HeadlineViewHolder private constructor(v: View) : RecyclerView.ViewHolder(v) {

    private val imageView = itemView.findViewById<ImageView>(R.id.iv_headline)
    private val headlineTitle = itemView.findViewById<TextView>(R.id.tv_headline)

    fun bind(article: Article) {
        if (!article.urlToImage.isNullOrEmpty()) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(ColorDrawable(Color.LTGRAY))
                .into(imageView)
        } else {
            imageView.setImageDrawable(ColorDrawable(Color.LTGRAY))
        }

        headlineTitle.text = article.title
    }

    companion object {
        fun from(parent: ViewGroup): HeadlineViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return HeadlineViewHolder(
                layoutInflater.inflate(R.layout.item_article_headline, parent, false)
            )
        }
    }
}

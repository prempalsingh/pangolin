package com.prempal.pangolin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by prempal on 2020-02-09.
 */
@Entity(tableName = "articles")
data class Article(

    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String? = "",

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String? = "",

    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String? = "",

    @SerializedName("url")
    @ColumnInfo(name = "url")
    val url: String? = "",

    @SerializedName("urlToImage")
    @ColumnInfo(name = "urlToImage")
    val urlToImage: String? = "",

    @SerializedName("publishedAt")
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = ""
) {

    // Keeping id outside constructor so it is excluded in equals() and hashCode()
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

package com.phonedeveloper.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @SerializedName("title")
    val title: String = " ",
    @SerializedName("abstract")
    val abstract: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("url")
    val url: String?,
    @SerializedName("published_date")
    val published_date: String?,
    @SerializedName("media")
    @Expose
    var media: List<Medium>? = null
) : Serializable

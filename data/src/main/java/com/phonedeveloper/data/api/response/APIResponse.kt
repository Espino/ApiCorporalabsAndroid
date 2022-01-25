package com.phonedeveloper.data.api.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.phonedeveloper.domain.model.Article
import java.io.Serializable

data class SearchNewsResponse(
    val response: BaseResponse?
)

data class BaseResponse(
    val docs: List<Article>?
)

data class APIResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null,

    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null,

    @SerializedName("results")
    @Expose
    var articleItems: List<Article>? = null

)

data class NewsModel(
    @SerializedName("abstract")
    val `abstract`: String? = null,
    @SerializedName("adx_keywords")
    val adxKeywords: String? = null,
    @SerializedName("asset_id")
    val assetId: Long? = null,
    val byline: String? = null,
    val column: Any? = null,
    @SerializedName("des_facet")
    val desFacet: List<String>? = null,
    @SerializedName("eta_id")
    val etaId: Int? = null,
    @SerializedName("geo_facet")
    val geoFacet: List<String>? = null,
    val id: Long? = null,
    @SerializedName("nytdsection")
    val nytdsection: String? = null,
    @SerializedName("org_facet")
    val orgFacet: List<String>? = null,
    @SerializedName("per_facet")
    val perFacet: List<String>? = null,
    @SerializedName("published_date")
    val publishedDate: String? = null,
    val section: String? = null,
    val source: String? = null,
    val subsection: String? = null,
    val title: String? = null,
    val uri: String? = null,
    val url: String? = null
) : Serializable
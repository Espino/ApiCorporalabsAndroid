package com.phonedeveloper.data.api

import com.phonedeveloper.data.api.response.APIResponse
import com.phonedeveloper.data.api.response.SearchNewsResponse
import com.phonedeveloper.domain.utils.Constants.Companion.apiKey
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NYTimesAPIService {

    @GET("/svc/mostpopular/v2/viewed/{period}.json$apiKey")
    suspend fun getArticles(@Path("period") period: Int): APIResponse
//    suspend fun getArticles(@Path("period") period: Int): : Flow<APIResponse>

    @GET("svc/search/v2/articlesearch.json")
    suspend fun searchNews(
        @Query("q") text: String,
        @Query("page") page: Int,
        @Query("sort") sort: String = "newest"
    ): SearchNewsResponse

}
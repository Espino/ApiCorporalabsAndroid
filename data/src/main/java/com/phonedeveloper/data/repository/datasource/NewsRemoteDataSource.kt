package com.phonedeveloper.data.repository.datasource

import androidx.paging.PagingSource
import com.phonedeveloper.data.api.response.APIResponse
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRemoteDataSource {

    /**
     * Paging Remote Mediator
     */
    //   paging
    fun getNewsArticlePagingFlow(): PagingSource<Int, Article>

    suspend fun getNewsArticlePagingOrderBy(
        orderBy: String,
        limit: Int,
        page: Int
    ): ResultDataResource<APIResponse>

    /**
     * Search
     */
    suspend fun getArticlesSearch(
        searchQuery: String,
        page: Int
    ): Response<Article>

    suspend fun getNewsArticleSearchFlowUseCase(param: String): Flow<ResultDataResource<Article>>

    // Detail Local id
    suspend fun getDetailArticleFlow(article: Article): Article
}
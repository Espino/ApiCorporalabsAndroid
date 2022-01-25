package com.phonedeveloper.data.repository.datasourceImpl

import androidx.paging.PagingSource
import com.phonedeveloper.data.api.NYTimesAPIService
import com.phonedeveloper.data.api.response.APIResponse
import com.phonedeveloper.data.core.extensions.base.BaseRemoteDataSource
import com.phonedeveloper.data.repository.datasource.NewsRemoteDataSource
import com.phonedeveloper.domain.core.enum.PeriodApiFilter
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRemoteDataSourceImpl(
        private val newsAPIService: NYTimesAPIService
): BaseRemoteDataSource(), NewsRemoteDataSource {
    /**
     * Paging
     */
    override fun getNewsArticlePagingFlow(): PagingSource<Int, Article> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsArticlePagingOrderBy(
        orderBy: String,
        limit: Int,
        page: Int
    ): ResultDataResource<APIResponse> {
        return safeApiCall {
            newsAPIService.getArticles(PeriodApiFilter.LAST_DAY.value)
        }
    }

    /**
     * Search
     */
    override suspend fun getArticlesSearch(searchQuery: String, page: Int): Response<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun getNewsArticleSearchFlowUseCase(param: String): Flow<ResultDataResource<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailArticleFlow(article: Article): Article {
        TODO("Not yet implemented")
    }
}
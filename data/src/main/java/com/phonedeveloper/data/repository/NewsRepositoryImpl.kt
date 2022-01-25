package com.phonedeveloper.data.repository

import androidx.paging.*
import com.phonedeveloper.data.core.extensions.executeFlowResult
import com.phonedeveloper.data.repository.datasource.NewsRemoteDataSource
import com.phonedeveloper.data.repository.paging.ApiPagingSource
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.repository.RepositoryDataStore
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
//    Si hubiera Local
//    private val newsLocalDataSource: NewsLocalDataSource
) : RepositoryDataStore {
    override fun getArticlesEntityPagingFlow(q: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ApiPagingSource(
                newsRemoteDataSource = newsRemoteDataSource)
            }).flow.map { pagingData ->
            pagingData.map {
                it
            }
        }
    }

    override suspend fun getSearchArticlesFlowUseCase(q: String): Flow<ResultDataResource<Article>> {
        return newsRemoteDataSource.getNewsArticleSearchFlowUseCase(q)
    }

    override fun getArticleDetailFlow(article: Article): Flow<ResultDataResource<Article>> {
        return executeFlowResult(
            result = { newsRemoteDataSource.getDetailArticleFlow(article) })
    }

}
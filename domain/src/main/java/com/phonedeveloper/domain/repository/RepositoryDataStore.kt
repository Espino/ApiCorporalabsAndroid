package com.phonedeveloper.domain.repository

import androidx.paging.PagingData
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.flow.Flow

interface RepositoryDataStore {

    fun getArticlesEntityPagingFlow(q: String): Flow<PagingData<Article>>

    //Search
    suspend fun getSearchArticlesFlowUseCase(q: String): Flow<ResultDataResource<Article>>

    //    Detail
    fun getArticleDetailFlow(article: Article): Flow<ResultDataResource<Article>>

}
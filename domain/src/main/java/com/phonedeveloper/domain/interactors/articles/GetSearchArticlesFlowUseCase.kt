package com.phonedeveloper.domain.interactors.articles

import androidx.paging.PagingData
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.repository.RepositoryDataStore
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.flow.Flow

class GetSearchArticlesFlowUseCase (private val heroesRepository: RepositoryDataStore)  {

    suspend fun getSearchArticlesFlowUseCase(q: String) : Flow<ResultDataResource<Article>> {
        return heroesRepository.getSearchArticlesFlowUseCase(q)
    }

    fun getSearchArticlesPagingDataUseCase(q: String) : Flow<PagingData<Article>> {
        return heroesRepository.getArticlesEntityPagingFlow(q)
    }

}

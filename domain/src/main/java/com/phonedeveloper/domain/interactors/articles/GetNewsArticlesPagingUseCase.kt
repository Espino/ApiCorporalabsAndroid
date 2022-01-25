package com.phonedeveloper.domain.interactors.articles

import androidx.paging.PagingData
import com.phonedeveloper.domain.interactors.base.UseCaseJob
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.repository.RepositoryDataStore
import kotlinx.coroutines.flow.Flow

class GetNewsArticlesPagingUseCase(private val heroesRepository: RepositoryDataStore) :
    UseCaseJob<String, Flow<PagingData<Article>>>() {
    override suspend fun executeOnBackground(params: String) =
        heroesRepository.getArticlesEntityPagingFlow(params)
}
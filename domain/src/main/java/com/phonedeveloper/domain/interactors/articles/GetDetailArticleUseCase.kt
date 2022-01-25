package com.phonedeveloper.domain.interactors.articles

import com.phonedeveloper.domain.interactors.base.UseCaseJob
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.repository.RepositoryDataStore
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.flow.Flow


class GetDetailArticleUseCase(private val heroesRepository: RepositoryDataStore) :
    UseCaseJob<Article, Flow<ResultDataResource<Article>>>() {

    override suspend fun executeOnBackground(params: Article): Flow<ResultDataResource<Article>> =
        heroesRepository.getArticleDetailFlow(params)
}
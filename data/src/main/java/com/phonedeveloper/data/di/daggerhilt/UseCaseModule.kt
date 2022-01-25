package com.phonedeveloper.data.di.daggerhilt

import com.phonedeveloper.domain.core.executor.JobExecutor
import com.phonedeveloper.domain.core.executor.ThreadExecutor
import com.phonedeveloper.domain.interactors.articles.GetDetailArticleUseCase
import com.phonedeveloper.domain.interactors.articles.GetNewsArticlesPagingUseCase
import com.phonedeveloper.domain.interactors.articles.GetSearchArticlesFlowUseCase
import com.phonedeveloper.domain.repository.RepositoryDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Singleton
    @Provides
    fun provideGetNewsArticlesPagingUseCase(
        RepositoryDataStore: RepositoryDataStore
    ): GetNewsArticlesPagingUseCase {
        return GetNewsArticlesPagingUseCase(RepositoryDataStore)
    }

    @Singleton
    @Provides
    fun provideGetDetailArticleUseCase(
        RepositoryDataStore: RepositoryDataStore
    ): GetDetailArticleUseCase {
        return GetDetailArticleUseCase(RepositoryDataStore)
    }

    @Singleton
    @Provides
    fun provideGetSearchArticlesFlowUseCase(
        RepositoryDataStore: RepositoryDataStore
    ): GetSearchArticlesFlowUseCase {
        return GetSearchArticlesFlowUseCase(RepositoryDataStore)
    }

}



















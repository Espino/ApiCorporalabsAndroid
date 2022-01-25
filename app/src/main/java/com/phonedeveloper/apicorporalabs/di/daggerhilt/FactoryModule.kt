package com.phonedeveloper.apicorporalabs.di.daggerhilt

import android.app.Application
import com.phonedeveloper.apicorporalabs.ui.detail.DetailViewModelFactory
import com.phonedeveloper.apicorporalabs.ui.viewmodel.NewsViewModelFactory
import com.phonedeveloper.domain.interactors.articles.GetDetailArticleUseCase
import com.phonedeveloper.domain.interactors.articles.GetNewsArticlesPagingUseCase
import com.phonedeveloper.domain.interactors.articles.GetSearchArticlesFlowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideHeroesViewModelFactory(
        application: Application,
        getNewsArticlesPagingUseCase: GetNewsArticlesPagingUseCase,
        getSearchArticlesFlowUseCase: GetSearchArticlesFlowUseCase,
        getDetailArticleUseCase: GetDetailArticleUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getNewsArticlesPagingUseCase,
            getSearchArticlesFlowUseCase,
            getDetailArticleUseCase
        )
    }

    @Singleton
    @Provides
    fun provideHeroDetailViewModelFactory(
        getDetailArticleUseCase: GetDetailArticleUseCase
    ): DetailViewModelFactory {
        return DetailViewModelFactory(
            getDetailArticleUseCase
        )
    }
}









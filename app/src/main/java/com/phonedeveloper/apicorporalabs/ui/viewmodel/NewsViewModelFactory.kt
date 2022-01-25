package com.phonedeveloper.apicorporalabs.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.phonedeveloper.apicorporalabs.ui.articles.HomeNewsViewModel
import com.phonedeveloper.domain.interactors.articles.GetDetailArticleUseCase
import com.phonedeveloper.domain.interactors.articles.GetNewsArticlesPagingUseCase
import com.phonedeveloper.domain.interactors.articles.GetSearchArticlesFlowUseCase


class NewsViewModelFactory(
    private val app: Application,
    private val getNewsArticlesPagingUseCase: GetNewsArticlesPagingUseCase,
    private val getSearchArticlesFlowUseCase: GetSearchArticlesFlowUseCase,
    private val getDetailArticleUseCase: GetDetailArticleUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeNewsViewModel::class.java) -> HomeNewsViewModel(
                app,
                getNewsArticlesPagingUseCase,
                getSearchArticlesFlowUseCase,
                getDetailArticleUseCase
            ) as T

            else -> throw IllegalArgumentException("View Model Not found")


        }
    }
}


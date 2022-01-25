package com.phonedeveloper.apicorporalabs.ui.articles

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.phonedeveloper.apicorporalabs.core.base.BaseViewModel
import com.phonedeveloper.domain.interactors.articles.GetDetailArticleUseCase
import com.phonedeveloper.domain.interactors.articles.GetNewsArticlesPagingUseCase
import com.phonedeveloper.domain.interactors.articles.GetSearchArticlesFlowUseCase
import com.phonedeveloper.domain.model.Article
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeNewsViewModel(
    private val app: Application,
    private val getNewsArticlesPagingUseCase: GetNewsArticlesPagingUseCase,
    private val getSearchArticlesFlowUseCase: GetSearchArticlesFlowUseCase,
    private val getDetailArticleUseCase: GetDetailArticleUseCase

) : BaseViewModel<Any>(app) {

    enum class Refresh {
        FORCE, NORMAL
    }

    sealed class Event {
        data class ShowErrorMessage(val error: Throwable) : Event()
    }

    private var searchJob: Job? = null
    var pagingData = 0

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }

    /**
     *  Api con PagingData y PagingRemote
     */

    suspend fun getArticlesPaging(params: String): Flow<PagingData<Article>> {
        return if (params.isNotEmpty())
            getSearchArticlesFlow(params)
        else
            getNewsArticlesPagingUseCase.executeOnBackground(params = params)
                .cachedIn(viewModelScope)
    }

    private suspend fun getSearchArticlesFlow(q: String): Flow<PagingData<Article>> {
        return getSearchArticlesFlowUseCase.getSearchArticlesPagingDataUseCase(q)
            .cachedIn(viewModelScope)
    }

    fun onManualRefresh() {
        viewModelScope.launch {
            getArticlesPaging("")
//                refreshTriggerChannel.send(Refresh.FORCE)
        }
    }

}






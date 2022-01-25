package com.phonedeveloper.apicorporalabs.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.phonedeveloper.apicorporalabs.core.base.BaseViewModel
import com.phonedeveloper.domain.interactors.articles.GetDetailArticleUseCase
import com.phonedeveloper.domain.model.Article
import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getDetailArticleUseCase: GetDetailArticleUseCase

) : BaseViewModel<Any>(Application()) {

    val articleDetail: LiveData<ResultDataResource<Article>>
        get() = _articleDetailMutable

    private val _articleDetailMutable: MutableLiveData<ResultDataResource<Article>> =
        MutableLiveData()

    fun getDetailArticle(articleDetail: Article) {
        viewModelScope.launch {
            getDetailArticleUseCase.executeOnBackground(articleDetail).onEach { articleDetailResponse ->
                _articleDetailMutable.value = articleDetailResponse
            }.launchIn(this)
        }
    }
}
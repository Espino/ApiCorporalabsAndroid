package com.phonedeveloper.apicorporalabs.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.phonedeveloper.apicorporalabs.ui.viewmodel.ViewModelFactory
import com.phonedeveloper.domain.interactors.articles.GetDetailArticleUseCase

class DetailViewModelFactory(
    private val getDetailArticleUseCase: GetDetailArticleUseCase
) : ViewModelFactory<DetailViewModel> {
    override fun create(handle: SavedStateHandle): DetailViewModel {
        return DetailViewModel(
            handle,
            getDetailArticleUseCase
        )
    }
}

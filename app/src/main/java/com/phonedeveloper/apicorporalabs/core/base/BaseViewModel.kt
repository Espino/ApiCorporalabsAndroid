package com.phonedeveloper.apicorporalabs.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


abstract class BaseViewModel<T>(app: Application): AndroidViewModel(app) {

// Shows or hide progress loading bar if the have it.
private val _isLoading = MutableLiveData(false)
    val isLoading : LiveData<Boolean>
        get() = _isLoading

    // Shows or hide and empty view layout if the view have it
    private val _showEmptyView = MutableLiveData(false)
    val showEmptyView : LiveData<Boolean>
        get() = _showEmptyView

    //Shows, hide, init or stop refreshing of Swipe refresh layout if the view have it.
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing : LiveData<Boolean>
        get() = _isRefreshing

    //Shows, hide, error message view.
    private val _showErrorCause = MutableLiveData(false)
    val showErrorCause : LiveData<Boolean>
        get() = _showErrorCause

    // The resource default value of the error or any error(Exception, server side, etc).
    private val _errorCause = MutableLiveData<Any>()
    val errorCause: LiveData<Any>
        get() = _errorCause


    /*
     * The following functions are just for presentation purposes
     */
    protected fun setRefreshing(refreshValue: Boolean) {
        _isRefreshing.value = refreshValue
    }

    protected fun showLoading(loadingValue: Boolean) {
        _isLoading.value = loadingValue
    }

    protected fun shouldShowEmptyView(show: Boolean?) {
        _showEmptyView.value = show
    }

    protected fun showErrorCause(show: Boolean) {
        _showErrorCause.value = show
    }
}
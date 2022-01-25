package com.phonedeveloper.domain.utils

sealed class ResultDataResource<out T>(
//    val status: Status,
    val data: T? = null,
    val error: ErrorType? = null,
    val messageError: String? = null
    ) {
    class Success<T>(data: T) : ResultDataResource<T>(data)
    data class SuccessDataResource<out T>(val dataStatus: T) : ResultDataResource<Any>(dataStatus)
//    data class SuccessDataUser<out T>(data: T) : ResultDataResource<T>(data)
    class Loading<T>(data: T? = null) : ResultDataResource<T>(data)
    class Error<T>(error: ErrorType? = null, data: T? = null) : ResultDataResource<T>(data, error)
    class ErrorMessage<T>(message: String, data: T? = null) : ResultDataResource<T>(data, messageError = message)
    data class ErrorString(val errorMessage: String? = null) : ResultDataResource<Nothing>()
    data class ErrorFailure(val failure: ResultDataResponse<Any>) : ResultDataResource<Nothing>()
    data class ErrorException(val exception: Exception) : ResultDataResource<Nothing>()


    sealed class ErrorType(
        val throwable: Throwable? = null,
        val message: Int? = null
    ) {
        class DatabaseError(throwable: Throwable? = null) : ErrorType(throwable)
        class IOError(throwable: Throwable? = null) : ErrorType(throwable)
        class HttpError(throwable: Throwable? = null, val statusCode: Int) : ErrorType(throwable)
        class Unknown(throwable: Throwable? = null) : ErrorType(throwable)
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        FAILURE
    }

    // string method to display a result for debugging
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is ErrorFailure -> "Error[exception=$failure]"
            else -> "Error[exception]"
        }
    }
}
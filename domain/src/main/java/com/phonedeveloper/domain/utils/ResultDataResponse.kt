package com.phonedeveloper.domain.utils

sealed class ResultDataResponse<out T : Any> {
    data class Success<T : Any>(val data: T?) : ResultDataResponse<T>()
    data class NetworkError(val error: String) : ResultDataResponse<Nothing>()
    data class TimeOutServerError(val error: String) : ResultDataResponse<Nothing>()
    data class ExceptionError(val errorCode: Exception) : ResultDataResponse<Nothing>()
    data class ServerError(val errorCode: String) : ResultDataResponse<Nothing>()
}
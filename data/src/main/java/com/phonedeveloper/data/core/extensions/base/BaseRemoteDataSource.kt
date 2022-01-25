package com.phonedeveloper.data.core.extensions.base

import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): ResultDataResource<T> {
                try {
                    val response = call()
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) return ResultDataResource.Success(body)
                    }
//                    var errorMsg = response.message()
//                    if (errorMsg.isEmpty()) {
//                        errorMsg = response.parseErrJsonResponse<ResponseErrMsg>()?.errorMessage.orEmpty()
//                    }
//                    return error("${response.code()}: $errorMsg")

                    return error(" ${response.code()} ${response.message()}")
                } catch (e: Exception) {
                    return error(e.message ?: e.toString())
                }
    }

    private fun <T> error(message: String): ResultDataResource<T> {
        Timber.d(message)
        return ResultDataResource.ErrorMessage("Network call has failed for a following reason: $message", null)
    }


    companion object {
        const val NO_INTERNET_ERROR = "Oops!! your internet is lost"
        const val GENERIC_ERROR = "Oops!! something's wrong"
        const val CLIENT_ERROR = "Oops!! we can't resolve your request"
        const val TIMEOUT_ERROR = "Oops!! internet to slow"
        const val SERVER_ERROR = "Oops!! something's wrong in our server, try later"
    }

    protected suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultDataResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultDataResource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        val result = when (throwable.code()) {
                            in 400..451 -> errorMessage(CLIENT_ERROR)
                            in 500..599 -> errorMessage(SERVER_ERROR)
                            else -> errorMessage(GENERIC_ERROR)
                        }
                        result
                    }
                    is ConnectException -> errorMessage(NO_INTERNET_ERROR)
                    is UnknownHostException -> errorMessage(NO_INTERNET_ERROR)
                    is SocketTimeoutException -> errorMessage(TIMEOUT_ERROR)
                    is IOException -> errorMessage(throwable.message)
                    else -> errorMessage(throwable.message)
                }
            }
        }
    }

    private fun errorMessage(errorMessage: String?): ResultDataResource.ErrorString {
        Timber.d(errorMessage)
        return ResultDataResource.ErrorString(errorMessage)
    }

}
package com.phonedeveloper.domain.interactors.base

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCaseResult<T>.() -> Unit

abstract class UseCaseJob<in Params, out T> {
    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var mainContext: CoroutineContext = Dispatchers.Main

    abstract suspend fun executeOnBackground(params: Params): T

    fun execute(block: CompletionBlock<T>, params: Params) {
        unsubscribe()
        parentJob = Job()
        CoroutineScope(mainContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground(params)
                }
                block(UseCaseResult.Success(result))
            } catch (cancellationException: CancellationException) {
                block(UseCaseResult.Error(cancellationException))
            }
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

}

sealed class UseCaseResult<out T> {
    class Success<out T>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}
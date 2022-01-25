package com.phonedeveloper.data.core.extensions

import com.phonedeveloper.domain.utils.ResultDataResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T> executeFlowResult(
    result: suspend () -> T,
): Flow<ResultDataResource<T>> =
    flow {
        emit(ResultDataResource.Loading())

        // emit cached value
        emit(ResultDataResource.Success(result.invoke()))
    }.flowOn(Dispatchers.IO)
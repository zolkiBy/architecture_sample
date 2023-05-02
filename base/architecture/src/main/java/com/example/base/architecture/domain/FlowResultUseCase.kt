package com.example.base.architecture.domain

import com.example.base.common.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowResultUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val nonCancellableScope: CoroutineScope? = null
) {
    operator fun invoke(parameters: P): Flow<Result<R, Exception>> = execute(parameters)
        .catch { e -> emit(Result.failure(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(parameters: P): Flow<Result<R, Exception>>
}
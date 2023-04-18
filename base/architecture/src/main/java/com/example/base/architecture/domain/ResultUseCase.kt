package com.example.base.architecture.domain

import com.example.base.common.result.Result
import com.example.base.common.result.resultOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext

abstract class ResultUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val scope: CoroutineScope,
) {

    protected abstract suspend fun execute(parameters: P): R

    suspend operator fun invoke(parameters: P): Result<R, Exception> =
        resultOf {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        }

    fun finish() {
        scope.coroutineContext.cancel()
    }
}

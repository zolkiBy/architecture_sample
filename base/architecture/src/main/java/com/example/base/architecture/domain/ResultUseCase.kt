package com.example.base.architecture.domain

import com.example.base.common.result.Result
import com.example.base.common.result.resultOf
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class ResultUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val applicationScope: CoroutineScope,
) {

    suspend operator fun invoke(parameters: P): Result<R, Exception> =
        resultOf {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        }

    protected abstract suspend fun execute(parameters: P): R

    suspend fun executeNonCancellableOperation(block: () -> Unit) {
        withContext(coroutineDispatcher) {
            applicationScope.launch { block() }.join()
        }
    }
}

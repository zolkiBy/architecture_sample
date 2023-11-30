package com.example.base.architecture.domain

import androidx.annotation.CheckResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NonCancellableUseCase<P>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val applicationScope: CoroutineScope,
) {

    suspend operator fun invoke(parameters: P) {
        withContext(coroutineDispatcher) {
            applicationScope.launch { executeNonCancellableOperation(parameters) }.join()
        }
    }

    @CheckResult
    protected abstract suspend fun executeNonCancellableOperation(parameters: P)
}
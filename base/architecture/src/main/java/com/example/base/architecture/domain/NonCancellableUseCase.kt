package com.example.base.architecture.domain

import androidx.annotation.CheckResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

abstract class NonCancellableUseCase<P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val applicationScope: CoroutineScope,
) {

    suspend operator fun invoke(parameters: P): R {
        return withContext(coroutineDispatcher) {
            applicationScope.async { executeNonCancellableOperation(parameters) }.await()
        }
    }

    @CheckResult
    protected abstract suspend fun executeNonCancellableOperation(parameters: P): R
}
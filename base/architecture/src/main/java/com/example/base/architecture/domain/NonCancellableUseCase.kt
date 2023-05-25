package com.example.base.architecture.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NonCancellableUseCase(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val applicationScope: CoroutineScope,
) {

    suspend operator fun invoke() {
        withContext(coroutineDispatcher) {
            applicationScope.launch { executeNonCancellableOperation() }.join()
        }
    }

    protected abstract suspend fun executeNonCancellableOperation()
}
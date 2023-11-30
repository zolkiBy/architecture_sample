package com.example.base.architecture.domain

import androidx.annotation.CheckResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

abstract class FlowUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(parameters: P): Flow<R> =
        withContext(coroutineDispatcher) {
            execute(parameters)
        }

    @CheckResult
    protected abstract suspend fun execute(parameters: P): Flow<R>
}
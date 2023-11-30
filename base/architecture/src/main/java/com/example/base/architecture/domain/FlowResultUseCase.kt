package com.example.base.architecture.domain

import androidx.annotation.CheckResult
import com.example.base.common.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.cancellation.CancellationException

abstract class FlowResultUseCase<in P, R>(
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    operator fun invoke(parameters: P): Flow<Result<R, Exception>> {
        //TODO: check the result
        val result = try {
            Result.success(execute(parameters))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
        return execute(parameters)
            .catch { e -> emit(Result.failure(Exception(e))) }
            .flowOn(coroutineDispatcher)
    }


    @CheckResult
    protected abstract fun execute(parameters: P): Flow<Result<R, Exception>>
}
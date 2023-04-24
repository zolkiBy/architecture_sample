package com.example.base.common.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

suspend fun <T> Flow<T>.collect(
    onNext: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onComplete: () -> Unit = {}
) {
    this
        .onEach { onNext(it) }
        .onCompletion { error ->
            if (error == null) {
                onComplete()
            }
        }
        .catch { onError(it) }
        .collect()
}
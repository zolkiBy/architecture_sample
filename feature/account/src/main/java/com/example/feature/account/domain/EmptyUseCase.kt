package com.example.feature.account.domain

import com.example.base.architecture.domain.ResultUseCase
import com.example.feature.account.data.model.AccountData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class EmptyUseCase(coroutineDispatcher: CoroutineDispatcher, applicationScope: CoroutineScope) :
    ResultUseCase<AccountData, Unit>(coroutineDispatcher, applicationScope) {

    override suspend fun execute(parameters: AccountData): Unit {
        executeNonCancellableOperation {

        }
    }


}
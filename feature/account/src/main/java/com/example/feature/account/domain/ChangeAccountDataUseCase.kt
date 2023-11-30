package com.example.feature.account.domain

import com.example.base.architecture.domain.NonCancellableUseCase
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class ChangeAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    applicationScope: CoroutineScope,
    private val accountRepository: AccountRepository,
) : NonCancellableUseCase<ChangeAccountDataUseCase.ChangeAccountDataParameters>(
    coroutineDispatcher,
    applicationScope
) {

    override suspend fun executeNonCancellableOperation(parameters: ChangeAccountDataParameters) {
        Timber.d("Start changing account data")
        accountRepository.changeAccountData(parameters.appId, parameters.requestsAmount)
        Timber.d("Finish changing account data")
    }

    data class ChangeAccountDataParameters(val appId: String, val requestsAmount: Long)
}


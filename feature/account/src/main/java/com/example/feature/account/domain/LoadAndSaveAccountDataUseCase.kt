package com.example.feature.account.domain

import com.example.base.architecture.domain.NonCancellableUseCase
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class LoadAndSaveAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    applicationScope: CoroutineScope,
    private val accountRepository: AccountRepository,
) : NonCancellableUseCase<Unit>(coroutineDispatcher, applicationScope) {

    override suspend fun executeNonCancellableOperation(parameters: Unit) {
        Timber.d("Start loading data from network and then save it in the persistent storage")
        accountRepository.loadAndSaveAccountData()
        Timber.d("Finish saving data")
    }
}
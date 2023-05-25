package com.example.feature.account.domain

import com.example.base.architecture.domain.NonCancellableUseCase
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import timber.log.Timber

class SaveAccountDataPersistentUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    applicationScope: CoroutineScope,
    private val accountRepository: AccountRepository,
) : NonCancellableUseCase(coroutineDispatcher, applicationScope) {

    override suspend fun executeNonCancellableOperation() {
        Timber.d("Start load data from network and then save it in the persistent storage")
        delay(4000L)
        accountRepository.saveAccountData()
        Timber.d("Finish saving data")
    }
}
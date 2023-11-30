package com.example.feature.account.domain

import com.example.base.architecture.domain.NonCancellableUseCase
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber

class DeleteAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    applicationScope: CoroutineScope,
    private val accountRepository: AccountRepository,
): NonCancellableUseCase<Unit>(coroutineDispatcher, applicationScope) {

    override suspend fun executeNonCancellableOperation(parameters: Unit) {
        Timber.d("Start deleting account data")
        accountRepository.deleteAccountData()
        Timber.d("Finish deleting account data")
    }
}
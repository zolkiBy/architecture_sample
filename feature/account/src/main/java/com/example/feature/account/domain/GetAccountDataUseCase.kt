package com.example.feature.account.domain

import com.example.base.architecture.domain.FlowUseCase
import com.example.base.model.AccountData
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class GetAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val accountRepository: AccountRepository,
) : FlowUseCase<Unit, AccountData>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<AccountData> {
        return accountRepository.getAccountData()
    }
}
package com.example.feature.account.domain

import com.example.base.architecture.domain.FlowResultUseCase
import com.example.base.common.result.Result
import com.example.feature.account.data.model.AccountData
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    coroutineScope: CoroutineScope,
    private val accountRepository: AccountRepository,
) : FlowResultUseCase<Unit, AccountData>(coroutineDispatcher, coroutineScope) {
    override fun execute(parameters: Unit): Flow<Result<AccountData, Exception>> {
        return flow {
            emit(accountRepository.getAccountData())
        }
    }
}
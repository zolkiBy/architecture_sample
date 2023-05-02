package com.example.feature.account.domain

import com.example.base.architecture.domain.FlowResultUseCase
import com.example.base.common.result.Result
import com.example.feature.account.data.model.AccountData
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GetAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val accountRepository: AccountRepository,
) : FlowResultUseCase<Unit, AccountData>(coroutineDispatcher) {
    override fun execute(parameters: Unit): Flow<Result<AccountData, Exception>> {
        return flow {
            emit(accountRepository.getAccountData())
        }
    }
}
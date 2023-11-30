package com.example.feature.account.domain

import com.example.base.architecture.domain.FlowUseCase
import com.example.base.model.AccountData
import com.example.feature.account.data.EmptyAccountDataException
import com.example.feature.account.data.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAccountDataUseCase(
    coroutineDispatcher: CoroutineDispatcher,
    private val accountRepository: AccountRepository,
    private val deleteAccountDataUseCase: DeleteAccountDataUseCase,
    private val loadAndSaveAccountDataUseCase: LoadAndSaveAccountDataUseCase,
) : FlowUseCase<Unit, AccountData>(coroutineDispatcher) {

    override suspend fun execute(parameters: Unit): Flow<AccountData> {
        deleteAccountDataUseCase.invoke(Unit)
        loadAndSaveAccountDataUseCase.invoke(Unit)

        return accountRepository.getAccountData().map { accountData ->
            accountData ?: throw EmptyAccountDataException()
        }
    }
}
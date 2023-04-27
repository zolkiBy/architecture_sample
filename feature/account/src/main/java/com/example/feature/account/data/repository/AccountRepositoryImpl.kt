package com.example.feature.account.data.repository

import com.example.base.common.result.Result
import com.example.base.network.exceptions.ApiException
import com.example.feature.account.data.model.AccountData
import com.example.feature.account.data.net.AccountApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

class AccountRepositoryImpl(private val accountApi: AccountApi, private val coroutineDispatcher: CoroutineDispatcher) : AccountRepository {
    override suspend fun getAccountData(): Result<AccountData, Exception> {
        return withContext(coroutineDispatcher) {
            val response = accountApi.getUsage()
            val result = if (response.isSuccessful &&
                response.body() != null &&
                response.body()?.isSuccess() == true
            ) {
                response.body()?.toAccountData()?.let { accountData ->
                    Result.success(accountData)
                } ?: Result.failure(ApiException())
            } else {
                Result.failure(ApiException())
            }

            result
        }
    }
}
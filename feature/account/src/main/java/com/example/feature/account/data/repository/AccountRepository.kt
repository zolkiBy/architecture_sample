package com.example.feature.account.data.repository

import com.example.base.model.AccountData
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccountData(): Flow<AccountData?>

    suspend fun deleteAccountData()

    suspend fun loadAndSaveAccountData()

    // TODO: to think about returning boolean for successful or unsuccessful operation
    suspend fun changeAccountData(appId: String, requestsAmount: Long)
}
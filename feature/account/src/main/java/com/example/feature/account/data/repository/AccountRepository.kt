package com.example.feature.account.data.repository

import com.example.base.model.AccountData
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getAccountData(): Flow<AccountData>

    suspend fun saveAccountData()
}
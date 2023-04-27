package com.example.feature.account.data.repository

import com.example.base.common.result.Result
import com.example.feature.account.data.model.AccountData
import java.lang.Exception

interface AccountRepository {
    suspend fun getAccountData(): Result<AccountData, Exception>
}
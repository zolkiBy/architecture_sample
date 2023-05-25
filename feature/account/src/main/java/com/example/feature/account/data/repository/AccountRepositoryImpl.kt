package com.example.feature.account.data.repository

import com.example.base.model.AccountData
import com.example.base.persistentstorage.dao.AccountDataDao
import com.example.base.persistentstorage.model.asEntity
import com.example.base.persistentstorage.model.toExternalModel
import com.example.feature.account.data.net.AccountApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

class AccountRepositoryImpl(
    private val accountApi: AccountApi,
    private val accountDataDao: AccountDataDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) : AccountRepository {
    override suspend fun getAccountData(): Flow<AccountData> {
        return withContext(coroutineDispatcher) {
            accountDataDao.getAccountData()
                .map {
                    it.toExternalModel()
                }
        }
    }

    override suspend fun saveAccountData() {
        withContext(coroutineDispatcher) {
            val response = accountApi.getUsage()
            if (response.isSuccessful &&
                response.body() != null &&
                response.body()?.isSuccess() == true
            ) {
                Timber.d("Get usage response success")
                response.body()?.toAccountData()?.let { accountData ->
                    Timber.d("Get usage response data: $accountData")
                    accountDataDao.upsertAccountData(accountData.asEntity())
                }
            }
        }
    }
}
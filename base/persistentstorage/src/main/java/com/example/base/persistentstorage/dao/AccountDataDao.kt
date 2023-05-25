package com.example.base.persistentstorage.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.base.persistentstorage.model.AccountDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDataDao {

    @Query("SELECT * FROM account_data")
    fun getAccountData(): Flow<AccountDataEntity>

    @Upsert
    suspend fun upsertAccountData(accountData: AccountDataEntity)
}
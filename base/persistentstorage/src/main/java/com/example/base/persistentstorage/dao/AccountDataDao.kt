package com.example.base.persistentstorage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.base.persistentstorage.model.AccountDataEntity
import com.example.base.persistentstorage.model.AccountDataRequests
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDataDao {

    @Query("SELECT * FROM account_data")
    fun getAccountData(): Flow<AccountDataEntity?>

    @Query("DELETE FROM account_data")
    fun deleteAccountData()

    @Upsert
    suspend fun upsertAccountData(accountData: AccountDataEntity)

    @Update(entity = AccountDataEntity::class)
    suspend fun updateAccountData(accountDataRequests: AccountDataRequests)
}
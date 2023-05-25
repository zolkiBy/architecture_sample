package com.example.base.persistentstorage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.base.model.AccountData

@Entity(tableName = "account_data")
data class AccountDataEntity(
    @PrimaryKey
    @ColumnInfo(name = "app_id")
    val appId: String,
    val status: String,
    @ColumnInfo(name = "plan_name")
    val planName: String,
    @ColumnInfo(name = "plan_quota")
    val planQuota: String,
    val requests: Long,
    @ColumnInfo(name = "requests_quota")
    val requestsQuota: Long,
    @ColumnInfo(name = "requests_remaining")
    val requestsRemaining: Long,
    @ColumnInfo(name = "daily_average_usage")
    val dailyAverageUsage: Long,
)

fun AccountDataEntity.toExternalModel() = AccountData(
    appId,
    status,
    planName,
    planQuota,
    requests,
    requestsQuota,
    requestsRemaining,
    dailyAverageUsage
)

fun AccountData.asEntity() = AccountDataEntity(
    appId,
    status,
    planName,
    planQuota,
    requests,
    requestsQuota,
    requestsRemaining,
    dailyAverageUsage,
)
package com.example.base.model

data class AccountData(
    val appId: String,
    val status: String,
    val planName: String,
    val planQuota: String,
    val requests: Long,
    val requestsQuota: Long,
    val requestsRemaining: Long,
    val dailyAverageUsage: Long,
)
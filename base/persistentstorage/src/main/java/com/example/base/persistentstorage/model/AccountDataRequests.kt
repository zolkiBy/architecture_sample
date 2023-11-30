package com.example.base.persistentstorage.model

import androidx.room.ColumnInfo

data class AccountDataRequests(
    @ColumnInfo(name = "app_id")
    val appId: String,
    val requests: Long,
)
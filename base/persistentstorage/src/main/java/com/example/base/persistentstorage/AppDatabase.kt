package com.example.base.persistentstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.base.persistentstorage.dao.AccountDataDao
import com.example.base.persistentstorage.model.AccountDataEntity

@Database(entities = [AccountDataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDataDao(): AccountDataDao
}
package com.example.base.persistentstorage.di

import androidx.room.Room
import com.example.base.persistentstorage.AppDatabase
import com.example.base.persistentstorage.dao.AccountDataDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistentStorageModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
    single { provideAccountDataDao(db = get()) }
}

fun provideAccountDataDao(db: AppDatabase): AccountDataDao = db.accountDataDao()

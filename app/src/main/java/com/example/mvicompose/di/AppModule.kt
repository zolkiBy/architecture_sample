package com.example.mvicompose.di

import com.example.base.navigation.NavigationActivityProvider
import com.example.feature.account.api.AccountStarter
import com.example.feature.account.routing.AccountStarterImpl
import com.example.mvicompose.App
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { App.instance.nonCancellableAppScope }
    single<AccountStarter> { AccountStarterImpl() }
    single(createdAtStart = true) { NavigationActivityProvider(androidApplication()) }
}

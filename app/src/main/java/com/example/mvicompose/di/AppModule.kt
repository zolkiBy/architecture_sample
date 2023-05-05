package com.example.mvicompose.di

import com.example.base.common.navigation.Navigator
import com.example.feature.account.api.AccountStarter
import com.example.feature.account.routing.AccountStarterImpl
import com.example.mvicompose.App
import com.example.mvicompose.navigation.GlobalNavigator
import org.koin.dsl.module

val appModule = module {
    single { App.instance.nonCancellableAppScope }
    single<AccountStarter> { AccountStarterImpl() }
    single<Navigator> { GlobalNavigator(accountFeatureStarter = get()) }
}

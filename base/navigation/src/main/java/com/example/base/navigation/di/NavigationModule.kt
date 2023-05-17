package com.example.base.navigation.di

import androidx.navigation.NavController
import com.example.base.navigation.NavigationActivityProvider
import com.example.base.navigation.api.Navigator
import com.example.base.navigation.navigators.AccountNavigator
import com.example.base.navigation.navigators.RatesNavigator
import com.example.feature.account.api.AccountDirection
import com.example.feature.account.api.AccountStarter
import com.example.feature.account.routing.AccountStarterImpl
import com.example.feature.rates.api.RatesDirection
import org.koin.dsl.module

val navigationModule = module {
    factory { provideNavController(activityProvider = get()) }
    single<AccountStarter> { AccountStarterImpl() }
    single<Navigator<AccountDirection>> { AccountNavigator(navController = get()) }
    single<Navigator<RatesDirection>> { RatesNavigator(accountFeatureStarter = get(), navController = get()) }
}

private fun provideNavController(activityProvider: NavigationActivityProvider): NavController =
    activityProvider.get()?.navigationController() ?: error("Navigation controller is not available")
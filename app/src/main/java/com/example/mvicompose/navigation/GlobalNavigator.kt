package com.example.mvicompose.navigation

import androidx.navigation.NavController
import com.example.base.common.navigation.Navigator
import com.example.feature.account.api.AccountStarter

class GlobalNavigator(private val accountFeatureStarter: AccountStarter) : Navigator {

    override fun startAccountFeature(navController: NavController) {
        accountFeatureStarter.start(navController)
    }
}
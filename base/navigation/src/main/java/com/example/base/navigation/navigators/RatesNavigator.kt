package com.example.base.navigation.navigators

import androidx.navigation.NavController
import com.example.base.navigation.api.Navigator
import com.example.feature.account.api.AccountStarter
import com.example.feature.rates.api.RatesDirection

class RatesNavigator(
    private val accountFeatureStarter: AccountStarter,
    private val navController: NavController
) : Navigator<RatesDirection> {

    override fun navigate(direction: RatesDirection) {
        when (direction) {
            RatesDirection.ToAccount -> {
                accountFeatureStarter.start(navController)
            }
        }
    }
}
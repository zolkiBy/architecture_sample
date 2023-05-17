package com.example.base.navigation.navigators

import androidx.navigation.NavController
import com.example.base.navigation.api.Navigator
import com.example.feature.account.api.AccountDirection

class AccountNavigator(private val navController: NavController) : Navigator<AccountDirection> {

    override fun navigate(direction: AccountDirection) {
        when (direction) {
            AccountDirection.Up -> navController.navigateUp()
        }
    }
}
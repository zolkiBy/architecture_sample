package com.example.feature.account.routing

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import com.example.feature.account.R
import com.example.feature.account.api.AccountStarter

class AccountStarterImpl : AccountStarter {
    override fun start(navController: NavController) {
        val request = NavDeepLinkRequest.Builder
            .fromUri(navController.context.getString(R.string.nav_deep_link).toUri())
            .build()
        navController.navigate(request)
    }
}
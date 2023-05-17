package com.example.mvicompose.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.base.navigation.NavigationActivity
import com.example.mvicompose.R
import com.example.mvicompose.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationActivity {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind, R.id.container)

    private var isLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            isLoaded = simulateInitializing()
        }

        splashScreen.setKeepOnScreenCondition { !isLoaded }

        setupToolbarWithNavController()
    }

    private suspend fun simulateInitializing(): Boolean {
        delay(3000L)
        return true
    }

    private fun setupToolbarWithNavController() {
        val navController = (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun navigationController(): NavController =
        findNavController(binding.navHostFragment.id)
}
package com.example.rates.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature.rates.R
import com.example.feature.rates.databinding.FragmentExchangeRatesViewsBinding
import com.example.rates.di.ratesModule
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange_rates_views) {

    private val viewModel: ExchangeRatesViewModel by viewModel()
    private val binding by viewBinding(FragmentExchangeRatesViewsBinding::bind, R.id.container)

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //TODO: need to find proper place to unload module in case of several screens
        loadKoinModules(ratesModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getDataBtn.setOnClickListener { viewModel.onBtnClicked() }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    Timber.d("Get UI state:$state")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(ratesModule)
    }
}
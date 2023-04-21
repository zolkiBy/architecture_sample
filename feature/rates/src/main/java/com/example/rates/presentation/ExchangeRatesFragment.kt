package com.example.rates.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature.rates.R
import com.example.feature.rates.databinding.FragmentExchangeRatesViewsBinding
import com.example.rates.di.ratesModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

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

        binding.getDataBtn.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(ratesModule)
    }
}
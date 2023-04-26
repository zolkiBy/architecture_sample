package com.example.rates.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature.rates.R
import com.example.feature.rates.databinding.FragmentExchangeRatesViewsBinding
import com.example.rates.di.ratesModule
import com.example.ui.views.extensions.shortFadeIn
import com.example.ui.views.extensions.shortFadeOut
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange_rates_views) {

    private val viewModel: ExchangeRatesViewModel by viewModel()

    private val binding by viewBinding(FragmentExchangeRatesViewsBinding::bind, R.id.container)
    private val currenciesAdapter = ExchangeRatesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        //TODO: need to find proper place to unload module in case of several screens
        loadKoinModules(ratesModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getDataBtn.setOnClickListener { viewModel.onBtnClicked() }

        initCurrenciesList()

        resources.getInteger(android.R.integer.config_shortAnimTime)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    Timber.d("Get UI state:$state")
                    when (state) {
                        is ExchangeRatesUiState.Success -> {
                            if (state.rates.rates.isEmpty()) {
                                //TODO: show empty reates view
                            } else {
                                currenciesAdapter.submitList(state.rates.rates)
                            }
                        }

                        is ExchangeRatesUiState.Error -> {
                            showSnackbar()
                        }

                        is ExchangeRatesUiState.Loading -> {
                            showLoading(state.isLoading)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(ratesModule)
    }

    private fun initCurrenciesList() {
        val linerLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItem = DividerItemDecoration(requireContext(), linerLayoutManager.orientation)
        with(binding.currenciesView) {
            layoutManager = linerLayoutManager
            adapter = currenciesAdapter
            addItemDecoration(dividerItem)
        }
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            binding.coverView.shortFadeIn()
            binding.progressIndicator.shortFadeIn()
        } else {
            binding.coverView.shortFadeOut()
            binding.progressIndicator.shortFadeOut()
        }
    }

    private fun showSnackbar() {
        Snackbar.make(binding.root, R.string.fragment_rates_loading_error, Snackbar.LENGTH_SHORT).show()
    }
}
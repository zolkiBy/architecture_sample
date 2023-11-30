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
import com.example.base.navigation.api.Navigator
import com.example.feature.rates.R
import com.example.feature.rates.databinding.FragmentExchangeRatesViewsBinding
import com.example.rates.di.ratesModule
import com.example.feature.rates.api.RatesDirection
import com.example.ui.views.delegates.ShowSnackbarDelegate
import com.example.ui.views.delegates.ShowSnackbarDelegateImpl
import com.example.ui.views.delegates.ViewVisibilityAnimatorDelegate
import com.example.ui.views.delegates.ViewVisibilityAnimatorDelegateImpl
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class ExchangeRatesFragment : Fragment(R.layout.fragment_exchange_rates_views),
    ViewVisibilityAnimatorDelegate by ViewVisibilityAnimatorDelegateImpl(),
    ShowSnackbarDelegate by ShowSnackbarDelegateImpl() {

    private val viewModel: ExchangeRatesViewModel by viewModel()

    private val navigator: Navigator<RatesDirection> by inject()

    private val binding by viewBinding(FragmentExchangeRatesViewsBinding::bind, R.id.container)
    private val currenciesAdapter = ExchangeRatesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loadKoinModules(ratesModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getDataBtn.setOnClickListener { viewModel.onBtnClicked() }
        binding.goToAccountBtn.setOnClickListener { navigator.navigate(RatesDirection.ToAccount) }

        initCurrenciesList()

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    Timber.d("Get UI state:$state")
                    when (state) {
                        is ExchangeRatesUiState.Success -> {
                            val rates = state.rates.rates
                            if (rates.isEmpty()) {
                                //TODO: show empty rates view
                            } else {
                                currenciesAdapter.submitList(rates)
                            }
                        }

                        is ExchangeRatesUiState.Error -> showShortSnackbar(
                            binding.root,
                            R.string.fragment_rates_loading_error
                        )

                        is ExchangeRatesUiState.Loading -> showLoading(state.isLoading)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        //TODO: need to find proper place to unload module in case of several screens
        unloadKoinModules(ratesModule)
    }

    private fun initCurrenciesList() {
        val linerLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItem = DividerItemDecoration(requireContext(), linerLayoutManager.orientation)
        with(binding.currenciesView) {
            layoutManager = linerLayoutManager
            adapter = currenciesAdapter
            addItemDecoration(dividerItem)
        }
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            shortFadeIn(binding.coverView, binding.progressIndicator)
        } else {
            shortFadeOut(binding.coverView, binding.progressIndicator)
        }
    }
}
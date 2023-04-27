package com.example.rates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.common.extensions.collect
import com.example.base.common.result.onFailure
import com.example.base.common.result.onSuccess
import com.example.rates.data.model.ExchangeRates
import com.example.rates.domain.GetExchangeRatesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class ExchangeRatesViewModel(private val exchangeRatesUseCase: GetExchangeRatesUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<ExchangeRatesUiState> = MutableStateFlow(ExchangeRatesUiState.Success(ExchangeRates.empty()))

    //TODO: think about subscription count
    val uiState: StateFlow<ExchangeRatesUiState> = _uiState


    fun onBtnClicked() {
        _uiState.value = ExchangeRatesUiState.Loading(true)
        viewModelScope.launch {
            exchangeRatesUseCase(GetExchangeRatesUseCase.Params(2022))
                .collect(
                    onNext = { result ->
                        Timber.d("Collect exchange rates: $result")
                        _uiState.value = ExchangeRatesUiState.Loading(false)
                        result.onSuccess { exchangeRates ->
                            Timber.d("Result success, exchange rates: $exchangeRates")
                            _uiState.value = ExchangeRatesUiState.Success(exchangeRates)
                        }.onFailure {
                            Timber.d("Result failure: $it")
                            _uiState.value = ExchangeRatesUiState.Error(it)
                        }
                    },
                    onError = { exception ->
                        Timber.d("Error collecting data: $exception")
                        _uiState.value = ExchangeRatesUiState.Loading(false)
                        _uiState.value = ExchangeRatesUiState.Error(exception)
                    }
                )
        }
    }
}

sealed class ExchangeRatesUiState {
    data class Success(val rates: ExchangeRates) : ExchangeRatesUiState()
    data class Error(val exception: Throwable) : ExchangeRatesUiState()
    data class Loading(val isLoading: Boolean) : ExchangeRatesUiState()
}
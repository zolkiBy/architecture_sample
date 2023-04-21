package com.example.rates.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.common.result.onFailure
import com.example.base.common.result.onSuccess
import com.example.rates.domain.GetExchangeRatesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ExchangeRatesViewModel(private val exchangeRatesUseCase: GetExchangeRatesUseCase) : ViewModel() {


    fun onBtnClicked() {
        viewModelScope.launch {
            exchangeRatesUseCase(GetExchangeRatesUseCase.Params(2022))
                .onEach {

                }
                .catch {

                }
                .collect { result ->
                    result.onSuccess {

                    }.onFailure {

                    }
                }

        }
    }

}
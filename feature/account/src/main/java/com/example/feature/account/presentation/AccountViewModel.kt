package com.example.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.common.extensions.collect
import com.example.feature.account.R
import com.example.feature.account.data.model.AccountDataItem
import com.example.feature.account.domain.GetAccountDataUseCase
import com.example.feature.account.domain.SaveAccountDataPersistentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class AccountViewModel(
    private val getAccountDataUseCase: GetAccountDataUseCase,
    private val saveAccountDataPersistentUseCase: SaveAccountDataPersistentUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading(true))

    val uiState: StateFlow<AccountUiState> = _uiState

    init {
        _uiState.value = AccountUiState.Loading(true)
        viewModelScope.launch {
            getAccountDataUseCase(Unit).collect(
                onNext = { accountData ->
                    Timber.d("Collect account data: $accountData")
                    _uiState.value = AccountUiState.Loading(false)
                    val accountItems = listOf(
                        AccountDataItem(R.string.fragment_account_item_app_id, accountData.appId),
                        AccountDataItem(R.string.fragment_account_item_status, accountData.status),
                        AccountDataItem(R.string.fragment_account_item_plan, accountData.planName),
                        AccountDataItem(R.string.fragment_account_item_plan_quota, accountData.planQuota),
                        AccountDataItem(R.string.fragment_account_item_number_requests, accountData.requests.toString()),
                        AccountDataItem(R.string.fragment_account_item_requests_quota, accountData.requestsQuota.toString()),
                        AccountDataItem(R.string.fragment_account_item_requests_remaining, accountData.requestsRemaining.toString()),
                        AccountDataItem(R.string.fragment_account_item_requests_daily_average, accountData.dailyAverageUsage.toString()),
                    )
                    _uiState.value = AccountUiState.Success(accountItems)
                },
                onError = { exception ->
                    if (exception is NullPointerException) {
                        Timber.d("No data in the storage, load data from network and then save it persistent")
                        loadAccountDataAndSaveItToPersistentStorage()
                    } else {
                        Timber.d("Error collecting account data: $exception")
                        _uiState.value = AccountUiState.Loading(false)
                        _uiState.value = AccountUiState.Error(exception)
                    }
                }
            )
        }
    }

    private fun loadAccountDataAndSaveItToPersistentStorage() {
        viewModelScope.launch { saveAccountDataPersistentUseCase.invoke() }
    }
}

sealed class AccountUiState {
    data class Success(val accountDataItems: List<AccountDataItem>) : AccountUiState()
    data class Error(val exception: Throwable) : AccountUiState()
    data class Loading(val isLoading: Boolean) : AccountUiState()
}
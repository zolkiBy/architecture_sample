package com.example.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.common.extensions.collect
import com.example.base.common.result.onFailure
import com.example.base.common.result.onSuccess
import com.example.feature.account.R
import com.example.feature.account.data.model.AccountDataItem
import com.example.feature.account.domain.GetAccountDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class AccountViewModel(private val accountDataUseCase: GetAccountDataUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading(true))

    val uiState: StateFlow<AccountUiState> = _uiState

    init {
        _uiState.value = AccountUiState.Loading(true)
        viewModelScope.launch {
            accountDataUseCase(Unit).collect(
                onNext = { result ->
                    Timber.d("Collect account data: $result")
                    _uiState.value = AccountUiState.Loading(false)
                    result.onSuccess { accountData ->
                        Timber.d("Result success, account data: $accountData")
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
                    }.onFailure { exception ->
                        Timber.d("Result failure: $exception")
                        _uiState.value = AccountUiState.Error(exception)
                    }
                },
                onError = { exception ->
                    Timber.d("Error collecting account data: $exception")
                    _uiState.value = AccountUiState.Loading(false)
                    _uiState.value = AccountUiState.Error(exception)
                }
            )
        }
    }
}

sealed class AccountUiState {
    data class Success(val accountDataItems: List<AccountDataItem>) : AccountUiState()
    data class Error(val exception: Throwable) : AccountUiState()
    data class Loading(val isLoading: Boolean) : AccountUiState()
}
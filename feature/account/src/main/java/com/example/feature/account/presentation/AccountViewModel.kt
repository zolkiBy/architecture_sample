package com.example.feature.account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.base.common.extensions.collect
import com.example.feature.account.R
import com.example.feature.account.data.model.AccountDataItem
import com.example.feature.account.domain.ChangeAccountDataUseCase
import com.example.feature.account.domain.GetAccountDataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.Exception

class AccountViewModel(
    private val getAccountDataUseCase: GetAccountDataUseCase,
    private val changeAccountDataUseCase: ChangeAccountDataUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<AccountUiState>(AccountUiState.Loading(true))

    val uiState: StateFlow<AccountUiState> = _uiState

    private var appId: String = ""

    init {
        _uiState.value = AccountUiState.Loading(true)
        viewModelScope.launch {
            getAccountDataUseCase(Unit)
                .collect(
                    onNext = { accountData ->
                        appId = accountData.appId
                        Timber.d("Collected account data: $accountData")
                        _uiState.value = AccountUiState.Loading(false)
                        val accountItems = listOf(
                            AccountDataItem(
                                R.string.fragment_account_item_app_id,
                                accountData.appId
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_status,
                                accountData.status
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_plan,
                                accountData.planName
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_plan_quota,
                                accountData.planQuota
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_number_requests,
                                accountData.requests.toString()
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_requests_quota,
                                accountData.requestsQuota.toString()
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_requests_remaining,
                                accountData.requestsRemaining.toString()
                            ),
                            AccountDataItem(
                                R.string.fragment_account_item_requests_daily_average,
                                accountData.dailyAverageUsage.toString()
                            ),
                        )
                        _uiState.value = AccountUiState.DataSuccessfullyLoaded(accountItems)
                    },
                    onError = { exception ->
                        Timber.d("Error collecting account data: $exception")
                        _uiState.value = AccountUiState.Loading(false)
                        _uiState.value = AccountUiState.Error(exception)
                    }
                )
        }
    }

    // TODO: check input value
    fun onChangeDataButtonClicked(requestsAmount: Long) {
        Timber.d("Change data button clicked with parameter: $requestsAmount")
        _uiState.value = AccountUiState.AccountDataChanging
        if (appId.isNotBlank()) {
            viewModelScope.launch {
                val dataChanged = changeAccountDataUseCase(
                    ChangeAccountDataUseCase.ChangeAccountDataParameters(
                        appId,
                        requestsAmount
                    )
                )
                if (dataChanged) {
                    _uiState.value = AccountUiState.AccountDataChanged()
                } else {
                    _uiState.value = AccountUiState.AccountDataChanged(Exception("Error when updating account data"))
                }
            }
        } else {
            _uiState.value = AccountUiState.AccountDataChanged(Exception("App Id is blank"))
        }
    }
}

sealed class AccountUiState {
    data class DataSuccessfullyLoaded(val accountDataItems: List<AccountDataItem>) : AccountUiState()
    data class Error(val exception: Throwable) : AccountUiState()
    data class Loading(val isLoading: Boolean) : AccountUiState()
    data object AccountDataChanging : AccountUiState()
    data class AccountDataChanged(val exception: Exception? = null) : AccountUiState()
}

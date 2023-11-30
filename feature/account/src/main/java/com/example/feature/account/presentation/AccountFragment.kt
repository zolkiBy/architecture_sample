package com.example.feature.account.presentation

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
import com.example.feature.account.R
import com.example.feature.account.databinding.FragmentAccountBinding
import com.example.feature.account.di.accountModule
import com.example.ui.views.delegates.ShowSnackbarDelegate
import com.example.ui.views.delegates.ShowSnackbarDelegateImpl
import com.example.ui.views.delegates.ViewVisibilityAnimatorDelegate
import com.example.ui.views.delegates.ViewVisibilityAnimatorDelegateImpl
import com.example.ui.views.extensions.disable
import com.example.ui.views.extensions.enable
import com.example.ui.views.extensions.invisible
import com.example.ui.views.extensions.visible
import kotlinx.coroutines.launch
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment(R.layout.fragment_account),
    ViewVisibilityAnimatorDelegate by ViewVisibilityAnimatorDelegateImpl(),
    ShowSnackbarDelegate by ShowSnackbarDelegateImpl() {

    private val viewModel: AccountViewModel by viewModel()

    private val binding by viewBinding(FragmentAccountBinding::bind, R.id.container)

    private val accountDataAdapter = AccountAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loadKoinModules(accountModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAccountDataList()

        binding.changeDataButton.setOnClickListener {
            // TODO: check input value
            val requestsAmount = binding.inputDataField.text.toString().toLong()
            viewModel.onChangeDataButtonClicked(requestsAmount)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    Timber.d("Collect state in fragment, state: $state")
                    when (state) {
                        is AccountUiState.Success -> {
                            val accountData = state.accountDataItems
                            if (accountData.isEmpty()) {
                                //TODO: show empty view
                            } else {
                                accountDataAdapter.submitList(accountData)
                            }
                        }

                        is AccountUiState.Error -> showShortSnackbar(binding.root, R.string.fragment_account_loading_error)
                        is AccountUiState.Loading -> showLoading(state.isLoading)
                        is AccountUiState.AccountDataChanging -> {
                            binding.changeDataProgressIndicator.visible()
                            binding.changeDataButton.disable()
                            binding.inputDataField.disable()
                        }
                        is AccountUiState.AccountDataHaveChanged -> {
                            binding.changeDataProgressIndicator.invisible()
                            binding.changeDataButton.enable()
                            binding.inputDataField.apply {
                                setText("")
                                enable()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun initAccountDataList() {
        val linerLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItem = DividerItemDecoration(requireContext(), linerLayoutManager.orientation)
        with(binding.accountData) {
            layoutManager = linerLayoutManager
            adapter = accountDataAdapter
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

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(accountModule)
    }
}
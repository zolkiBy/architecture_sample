package com.example.feature.account.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature.account.R
import com.example.feature.account.databinding.FragmentAccountBinding
import com.example.feature.account.di.accountModule
import kotlinx.coroutines.launch
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import timber.log.Timber

class AccountFragment : Fragment(R.layout.fragment_account) {
    private val viewModel: AccountViewModel by viewModels()

    private val binding by viewBinding(FragmentAccountBinding::bind, R.id.container)

    private val accountDataAdapter = AccountAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loadKoinModules(accountModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {state ->
                    Timber.d("Collect state in fragment, state: $state")
                    when(state){
                        is AccountUiState.Success -> {}
                        is AccountUiState.Error -> TODO()
                        is AccountUiState.Loading -> TODO()
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unloadKoinModules(accountModule)
    }
}
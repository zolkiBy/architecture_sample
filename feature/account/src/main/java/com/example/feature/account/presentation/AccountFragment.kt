package com.example.feature.account.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.feature.account.R
import com.example.feature.account.databinding.FragmentAccountBinding

class AccountFragment : Fragment(R.layout.fragment_account) {
    private val viewModel: AccountViewModel by viewModels()

    private val binding by viewBinding(FragmentAccountBinding::bind, R.id.container)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
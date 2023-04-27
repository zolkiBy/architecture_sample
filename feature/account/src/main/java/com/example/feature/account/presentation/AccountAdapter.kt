package com.example.feature.account.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.account.data.model.AccountData
import com.example.feature.account.data.model.AccountDataItem
import com.example.feature.account.databinding.ItemAccountBinding

class AccountAdapter : ListAdapter<AccountDataItem, AccountAdapter.AccountViewHolder>(AccountDataDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class AccountViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

    private class AccountDataDiffCallback : DiffUtil.ItemCallback<AccountDataItem>() {
        override fun areItemsTheSame(oldItem: AccountDataItem, newItem: AccountDataItem): Boolean = oldItem.nameResId == newItem.nameResId

        override fun areContentsTheSame(oldItem: AccountDataItem, newItem: AccountDataItem): Boolean = oldItem == newItem
    }
}
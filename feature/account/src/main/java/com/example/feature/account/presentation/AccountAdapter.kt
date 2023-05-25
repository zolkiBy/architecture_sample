package com.example.feature.account.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.account.data.model.AccountDataItem
import com.example.feature.account.databinding.ItemAccountBinding

class AccountAdapter : ListAdapter<AccountDataItem, AccountAdapter.AccountViewHolder>(AccountDataDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val binding = ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccountViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem)
    }

    class AccountViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(accountDataItem: AccountDataItem) {
            with(binding) {
                data.text = itemView.context.getString(accountDataItem.nameResId)
                value.text = accountDataItem.value
            }
        }
    }

    private class AccountDataDiffCallback : DiffUtil.ItemCallback<AccountDataItem>() {
        override fun areItemsTheSame(oldItem: AccountDataItem, newItem: AccountDataItem): Boolean = oldItem.nameResId == newItem.nameResId

        override fun areContentsTheSame(oldItem: AccountDataItem, newItem: AccountDataItem): Boolean = oldItem == newItem
    }
}
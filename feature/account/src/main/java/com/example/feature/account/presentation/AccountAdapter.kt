package com.example.feature.account.presentation

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.account.databinding.ItemAccountBinding

class AccountAdapter : ListAdapter<, AccountAdapter.AccountViewHolder>() {

    class AccountViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }
}
package com.example.rates.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.rates.databinding.ItemExchangeRateBinding
import com.example.rates.data.model.CurrencyRate

class ExchangeRatesAdapter : ListAdapter<CurrencyRate, ExchangeRatesAdapter.ExchangeRatesViewHolder>(ExchangeRatesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRatesViewHolder {
        val binding = ItemExchangeRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExchangeRatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExchangeRatesViewHolder, position: Int) {
        val currencyRate = getItem(position)
        holder.bind(currencyRate)
    }

    class ExchangeRatesViewHolder(private val binding: ItemExchangeRateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyRate: CurrencyRate) {
            with(binding) {
                this.currencyCode.text = currencyRate.code
                this.currencyRate.text = currencyRate.rate.toString()
            }
        }
    }

    private class ExchangeRatesDiffCallback : DiffUtil.ItemCallback<CurrencyRate>() {
        override fun areItemsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean = oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: CurrencyRate, newItem: CurrencyRate): Boolean = oldItem == newItem
    }
}
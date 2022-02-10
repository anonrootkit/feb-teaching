package com.example.feb.ui.home.logs.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.databinding.ListItemQuoteLogBinding

class QuoteLogsAdapter(
    private val inflater: LayoutInflater
) : ListAdapter<QuoteLog, QuoteLogsAdapter.QuoteLogViewHolder>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<QuoteLog>() {
        override fun areItemsTheSame(oldItem: QuoteLog, newItem: QuoteLog): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: QuoteLog, newItem: QuoteLog): Boolean {
            return oldItem == newItem
        }

    }

    class QuoteLogViewHolder(
        private val binding : ListItemQuoteLogBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(log : QuoteLog) {
            binding.date.text = "10 Feb, 2022"
            binding.quote.text = log.quote
            binding.quoteLogContainer.setBackgroundColor(Color.parseColor(log.color))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteLogViewHolder {
        return QuoteLogViewHolder(ListItemQuoteLogBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: QuoteLogViewHolder, position: Int) {
        val log = getItem(position)
        holder.bind(log)
    }
}
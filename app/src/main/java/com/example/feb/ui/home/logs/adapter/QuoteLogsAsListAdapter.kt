package com.example.feb.ui.home.logs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.databinding.ListItemQuoteLogBinding

class QuoteLogsAsListAdapter(
    private val inflater: LayoutInflater,
    private val onQuoteLongClicked : (QuoteLog) -> Unit,
) : ListAdapter<QuoteLog, QuoteLogsAsListAdapter.QuoteLogViewHolder>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<QuoteLog>() {
        override fun areItemsTheSame(oldItem: QuoteLog, newItem: QuoteLog): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: QuoteLog, newItem: QuoteLog): Boolean {
            return oldItem == newItem
        }

    }

    inner class QuoteLogViewHolder(
        private val binding : ListItemQuoteLogBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(log : QuoteLog) {
            binding.quoteLog = log
            binding.executePendingBindings()
        }

        init {
            binding.quoteLogContainer.setOnLongClickListener {
                val log = binding.quoteLog!!
                onQuoteLongClicked(log)
                true
            }
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
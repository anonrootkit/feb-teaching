package com.example.feb.ui.home.logs.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.databinding.FragmentQuoteLogsDuplicateBinding
import com.example.feb.databinding.ListItemQuoteLogBinding

class QuoteLogsAdapter(
    private val inflater: LayoutInflater,
    private val onQuoteLongClicked : (QuoteLog) -> Unit,
    private val onEditButtonIamge : (QuoteLog) -> Unit
) : ListAdapter<QuoteLog, QuoteLogsAdapter.QuoteLogViewHolder>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<QuoteLog>() {
        override fun areItemsTheSame(oldItem: QuoteLog, newItem: QuoteLog): Boolean {
            return oldItem.time == newItem.time
        }

        override fun areContentsTheSame(oldItem: QuoteLog, newItem: QuoteLog): Boolean {
            return oldItem == newItem
        }

    }

    inner class QuoteLogViewHolder(
        //private val binding : ListItemQuoteLogBinding
        private val binding : FragmentQuoteLogsDuplicateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(log : QuoteLog) {
            binding.quoteLog = log
            binding.executePendingBindings()
        }

        init {
            binding.editButtonImage.setOnClickListener{
                val log = binding.quoteLog!!
                onEditButtonIamge(log)
                true

            }
            binding.quoteLogContainer.setOnLongClickListener {
                val log = binding.quoteLog!!
                onQuoteLongClicked(log)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteLogViewHolder {
        //return QuoteLogViewHolder(ListItemQuoteLogBinding.inflate(inflater, parent, false))
        return QuoteLogViewHolder(FragmentQuoteLogsDuplicateBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: QuoteLogViewHolder, position: Int) {
        val log = getItem(position)
        holder.bind(log)
    }
}
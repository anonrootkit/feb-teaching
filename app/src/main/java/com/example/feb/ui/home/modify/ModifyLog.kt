package com.example.feb.ui.home.modify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.databinding.FragmentModifyLogBinding
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.ui.viewmodels.QuoteViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModifyLog : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentModifyLogBinding
    private lateinit var viewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModifyLogBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,
            QuoteViewModel.Factory(QuoteLogsRepository.getInstance(requireContext())))[QuoteViewModel::class.java]

        val quoteLog = requireArguments().getParcelable<QuoteLog>("quoteLog")!!
        binding.quoteLog = quoteLog

        binding.updateLog.setOnClickListener {
            updateNewQuote(quoteLog)
        }
    }

    private fun updateNewQuote(quoteLog: QuoteLog) {
        val updatedQuote = binding.updatedQuote.text?.toString()?.trim()

        if (updatedQuote.isNullOrBlank()) {
            Toast.makeText(requireContext(), "Quote cannot be blank", Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.updateLog(log = quoteLog.copy(quote = updatedQuote))
        dismiss()
    }

}
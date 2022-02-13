package com.example.feb.ui.home.logs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feb.R
import com.example.feb.databinding.FragmentQuoteLogsAsListBinding
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.ui.home.logs.adapter.QuoteLogsAsListAdapter
import com.example.feb.ui.viewmodels.QuoteViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuoteLogsAsList : Fragment(R.layout.fragment_quote_logs_as_list) {

    private lateinit var binding: FragmentQuoteLogsAsListBinding
    private lateinit var quoteViewModel: QuoteViewModel


    private val quoteLogsAdapter by lazy {
        QuoteLogsAsListAdapter(
            inflater = layoutInflater,
            onQuoteLongClicked = { log ->
                findNavController().navigate(R.id.action_quoteLogsAsList_to_modifyLog, Bundle().apply {
                    putParcelable("quoteLog", log)
                })
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuoteLogsAsListBinding.bind(view)
        binding.lifecycleOwner = this

        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModel.Factory(quoteLogsRepository = QuoteLogsRepository.getInstance(context = requireContext()))
        )[QuoteViewModel::class.java]

        binding.logsList.apply {
            adapter = quoteLogsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            quoteViewModel.getLogs().collect {
                if (!it.isNullOrEmpty()){
                    quoteLogsAdapter.submitList(it)
                }
            }
        }
    }

}
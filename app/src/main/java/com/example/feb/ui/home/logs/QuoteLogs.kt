package com.example.feb.ui.home.logs

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.feb.R
import com.example.feb.databinding.FragmentQuoteLogsBinding
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.ui.home.logs.adapter.QuoteLogsAdapter
import com.example.feb.ui.viewmodels.QuoteViewModel
import kotlinx.coroutines.launch

class QuoteLogs : Fragment(R.layout.fragment_quote_logs) {

    private lateinit var binding: FragmentQuoteLogsBinding
    private lateinit var quoteViewModel: QuoteViewModel

    private val quoteLogsAdapter by lazy {
        QuoteLogsAdapter(
            inflater = layoutInflater,
            onQuoteLongClicked = { log ->
                findNavController().navigate(R.id.action_quoteLogs_to_modifyLog, Bundle().apply {
                    putParcelable("quoteLog", log)
                })
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuoteLogsBinding.bind(view)
        binding.lifecycleOwner = this

        quoteViewModel = ViewModelProvider(
            this,
            QuoteViewModel.Factory(quoteLogsRepository = QuoteLogsRepository.getInstance(context = requireContext()))
        )[QuoteViewModel::class.java]

        binding.logsList.apply {
            adapter = quoteLogsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.logsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    val index =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    val color =
                        if (quoteLogsAdapter.currentList.isNotEmpty()) quoteLogsAdapter.currentList[index]?.color else null
                    if (color != null) {
                        requireActivity().window.statusBarColor = Color.parseColor(color)
                    }
                }
            }

        })

        quoteViewModel.getLogs().observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()){
                quoteLogsAdapter.submitList(it)
            }
        }

    }

}
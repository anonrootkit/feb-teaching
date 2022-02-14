package com.example.feb.ui.home.logs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.feb.R
import com.example.feb.databinding.FragmentQuoteLogsContainerBinding
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.ui.viewmodels.QuoteViewModel
import com.example.feb.utils.LogsListMode
import com.example.feb.utils.safeNavigate

class QuoteLogsContainer private constructor(): Fragment(R.layout.fragment_quote_logs_container) {

    companion object{
        fun getInstance() = QuoteLogsContainer()
    }

    private lateinit var quoteViewModel: QuoteViewModel

    private lateinit var binding : FragmentQuoteLogsContainerBinding
    private var isLogsListVisible = true

    private val logsNavController : NavController by lazy { Navigation.findNavController(binding.logScreenContainer) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuoteLogsContainerBinding.bind(view)
        binding.lifecycleOwner = this

        quoteViewModel = ViewModelProvider(this, QuoteViewModel.Factory(QuoteLogsRepository.getInstance(requireContext())))[QuoteViewModel::class.java]

        binding.toggleLogScreens.setOnClickListener {
            changeLogScreen()
        }
    }

    override fun onStart() {
        super.onStart()

        when(quoteViewModel.getLogsListMode()) {
            LogsListMode.LIST -> changeToList()
            LogsListMode.PAGE -> changeToPage()
        }
    }

    private fun changeToList() {
        quoteViewModel.setLogsListMode(mode = LogsListMode.LIST)
        logsNavController.safeNavigate(R.id.action_quoteLogsAsPage_to_quoteLogsAsList)
        isLogsListVisible = true
        binding.toggleLogScreens.setImageResource(R.drawable.ic_list)
    }

    private fun changeToPage() {
        quoteViewModel.setLogsListMode(mode = LogsListMode.PAGE)
        isLogsListVisible = false
        logsNavController.safeNavigate(R.id.action_quoteLogsAsList_to_quoteLogsAsPage2)
        binding.toggleLogScreens.setImageResource(R.drawable.ic_page)
    }


    private fun changeLogScreen() {
        if (isLogsListVisible) changeToPage()
        else changeToList()
    }
}
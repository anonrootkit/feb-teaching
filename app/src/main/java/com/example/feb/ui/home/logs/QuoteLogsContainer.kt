package com.example.feb.ui.home.logs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.feb.R
import com.example.feb.databinding.FragmentQuoteLogsContainerBinding

class QuoteLogsContainer : Fragment(R.layout.fragment_quote_logs_container) {

    private lateinit var binding : FragmentQuoteLogsContainerBinding
    private var isLogsListVisible = true

    private val logsNavController : NavController by lazy {
        Navigation.findNavController(binding.logScreenContainer)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuoteLogsContainerBinding.bind(view)
        binding.lifecycleOwner = this

        binding.toggleLogScreens.setOnClickListener {
            changeLogScreen()
        }

    }

    private fun changeLogScreen() {
        if (isLogsListVisible) {
            isLogsListVisible = false
            logsNavController.navigate(R.id.action_quoteLogsAsList_to_quoteLogsAsPage2)
            binding.toggleLogScreens.setImageResource(R.drawable.ic_page)
        }else{
            logsNavController.navigate(R.id.action_quoteLogsAsPage_to_quoteLogsAsList)
            isLogsListVisible = true
            binding.toggleLogScreens.setImageResource(R.drawable.ic_list)
        }
    }
}
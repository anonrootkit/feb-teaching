package com.example.feb.ui.home.quote

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.feb.R
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.databinding.FragmentQuotesBinding
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.ui.viewmodels.QuoteViewModel
import com.example.feb.utils.getRandomColor
import com.example.feb.utils.getRandomQuote

class Quotes private constructor() : Fragment(R.layout.fragment_quotes) {

    companion object{
        fun getInstance() = Quotes()
    }

    private lateinit var binding : FragmentQuotesBinding
    private lateinit var quotesViewModel: QuoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuotesBinding.bind(view)

        quotesViewModel = ViewModelProvider(this,
            QuoteViewModel.Factory(quoteLogsRepository = QuoteLogsRepository.getInstance(context = requireContext()))
        )[QuoteViewModel::class.java]

        binding.changeQuote.setOnClickListener { changeQuoteAndBgColor() }

        changeQuoteAndBgColor()
    }

    private fun changeQuoteAndBgColor() {
        val color = getRandomColor()
        val randomColor = Color.parseColor(color)
        val randomQuote = getRandomQuote()

        binding.quote.text = randomQuote
        binding.parent.setBackgroundColor(randomColor)

        val log = QuoteLog(time = System.currentTimeMillis(), color = color, quote = randomQuote)
        quotesViewModel.insertLog(log)
    }

}

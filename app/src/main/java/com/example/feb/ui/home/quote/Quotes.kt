package com.example.feb.ui.home.quote

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.feb.R
import com.example.feb.databinding.FragmentQuotesBinding
import com.example.feb.utils.getRandomColor
import com.example.feb.utils.getRandomQuote

class Quotes : Fragment(R.layout.fragment_quotes) {

    private lateinit var binding : FragmentQuotesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentQuotesBinding.bind(view)

        binding.changeQuote.setOnClickListener { changeQuoteAndBgColor() }

        changeQuoteAndBgColor()
    }

    private fun changeQuoteAndBgColor() {
        val randomColor = Color.parseColor(getRandomColor())
        val randomQuote = getRandomQuote()

        binding.quote.text = randomQuote
        binding.parent.setBackgroundColor(randomColor)
        requireActivity().window.statusBarColor = randomColor
    }

}

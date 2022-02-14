package com.example.feb.ui.home.temp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.feb.R
import com.example.feb.databinding.FragmentTemporaryBinding
import com.example.feb.ui.viewmodels.TempViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Temporary constructor() : Fragment(R.layout.fragment_temporary) {

    companion object {
        fun get() = Temporary()
    }

    private lateinit var binding: FragmentTemporaryBinding
    private lateinit var tempViewModel: TempViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTemporaryBinding.bind(view)
        binding.lifecycleOwner = this

        tempViewModel = ViewModelProvider(this, TempViewModel.Factory())[TempViewModel::class.java]

        binding.livedata.setOnClickListener {
            tempViewModel.livedataClicked()
        }

        binding.stateFlow.setOnClickListener {
            tempViewModel.stateFlowClicked()
        }

        binding.sharedFlow.setOnClickListener {
            tempViewModel.sharedFlowClicked()
        }

        binding.normalFlow.setOnClickListener {
            lifecycleScope.launch { collectNormalFlowResults() }
        }

        tempViewModel.livedata.observe(viewLifecycleOwner) {
            if (it != null)
                binding.livedataStatus.text = it
        }

        lifecycleScope.launch {
            tempViewModel.sharedFlow.collect {
                binding.sharedFlowStatus.text = it
            }
        }

        lifecycleScope.launch {
            tempViewModel.stateFlow.collect {
                if (it.isNotBlank())
                    binding.stateFlowStatus.text = it
            }

        }
    }

    suspend fun collectNormalFlowResults() {
        tempViewModel.getNormalFlow().collect {
            binding.normalFlowStatus.text = it
        }
    }
}
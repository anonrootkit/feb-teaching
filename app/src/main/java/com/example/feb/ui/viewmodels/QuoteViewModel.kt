@file:Suppress("UNCHECKED_CAST")

package com.example.feb.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.utils.LogsListMode
import kotlinx.coroutines.launch

class QuoteViewModel(
    private val quoteLogsRepository: QuoteLogsRepository
) : ViewModel() {

    fun insertLog(log: QuoteLog) {
        viewModelScope.launch {
            quoteLogsRepository.insertLog(log)
        }
    }

    fun updateLog(log : QuoteLog) {
        viewModelScope.launch {
            quoteLogsRepository.updateLog(log)
        }
    }

    fun getLogs() = quoteLogsRepository.getLogs()

    fun setLogsListMode(mode : LogsListMode) = quoteLogsRepository.setLogsListMode(mode)

    fun getLogsListMode() = quoteLogsRepository.getLogsListMode()


    class Factory(private val quoteLogsRepository: QuoteLogsRepository)
        : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return QuoteViewModel(quoteLogsRepository) as T
        }
    }
}
@file:Suppress("UNCHECKED_CAST")

package com.example.feb.ui.viewmodels

import androidx.lifecycle.*
import com.example.feb.data.db.entities.QuoteLog
import com.example.feb.domain.repo.QuoteLogsRepository
import com.example.feb.utils.LogsListMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class TempViewModel: ViewModel() {

    private val _livedata = MutableLiveData<String>()
    val livedata : LiveData<String>
        get() = _livedata

    private val _stateFlow = MutableStateFlow<String>("")
    val stateFlow : StateFlow<String> = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow : SharedFlow<String> = _sharedFlow.asSharedFlow()

    fun livedataClicked() {
        _livedata.value = "Livedata clickeddd!!!"
    }

    fun stateFlowClicked() {
        _stateFlow.value = "StateFlow clickeddd!!!"
    }

    fun sharedFlowClicked() {
        viewModelScope.launch {
            _sharedFlow.emit("${Random.nextInt()}")
        }
    }

    fun getNormalFlow() : Flow<String> {
        return flow {
            emit("Heeyyyy")
            delay(1000)
            emit("Byeeeee")
            delay(1000)
            emit("Hehehe")
        }
    }

    class Factory() : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TempViewModel() as T
        }
    }
}
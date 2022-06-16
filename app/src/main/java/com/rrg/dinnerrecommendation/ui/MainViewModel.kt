package com.rrg.dinnerrecommendation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _mainEventsChannel = Channel<MainEvents>()
    val mainEventChannel = _mainEventsChannel.receiveAsFlow()

    fun updateToolbar(title: String, hasBackAction: Boolean = true) = viewModelScope.launch {
        _mainEventsChannel.send(MainEvents.ToolbarEvents(title, hasBackAction))
    }

    sealed class MainEvents {
        data class ToolbarEvents(val title: String, val backAction: Boolean) : MainEvents()
    }
}

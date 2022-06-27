package com.rrg.dinnerrecommendation.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val showSplashScreen: MutableState<Boolean> = mutableStateOf(true)

    private val _mainEventsChannel = Channel<MainEvents>()
    val mainEventChannel = _mainEventsChannel.receiveAsFlow()

    fun updateToolbar(title: String, hasBackAction: Boolean = true, isCentered: Boolean = false) = viewModelScope.launch {
        _mainEventsChannel.send(MainEvents.ToolbarEvents(title, hasBackAction, isCentered))
    }

    sealed class MainEvents {
        data class ToolbarEvents(val title: String, val backAction: Boolean, val isCentered: Boolean) : MainEvents()
        object NoInternetEvents : MainEvents()
    }
}

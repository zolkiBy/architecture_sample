package com.example.base.architecture.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Ev : Event, Effect : UiEffect, out Reducer : BaseReducer<State, Ev>> : ViewModel() {
    abstract val state: Flow<State>
    abstract val reducer: Reducer

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun sendEvent(event: Ev) {
        reducer.sendEvent(event)
    }

    fun sendEffect(effect: Effect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
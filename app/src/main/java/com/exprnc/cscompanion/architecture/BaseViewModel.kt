package com.exprnc.cscompanion.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : ViewState, I : Intent>(defaultState: S) : ViewModel() {
    private val loadingCount = MutableStateFlow<Int>(0)

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Disabled)
    val loadingState = _loadingState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<ViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    protected fun emitEvent(event: ViewEvent) {
        viewModelScope.launch {
            _viewEvent.emit(event)
        }
    }

    private val _viewState = MutableStateFlow<S>(defaultState)
    val viewState = _viewState.asStateFlow()

    protected fun setState(state: S) {
        _viewState.value = state
    }

    abstract fun obtainIntent(intent: I)

    protected fun launchCoroutine(
        needLoader: Boolean = false,
        block: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            if (needLoader) {
                loadingCount.update { it + 1 }
            }
            block.invoke()
        }.invokeOnCompletion {
            if (needLoader) {
                loadingCount.update { if (it > 0) it - 1 else 0 }
            }
        }
    }

    init {
        loadingCount.onEach {
            if (it > 0) {
                _loadingState.update { LoadingState.Enabled }
            } else {
                _loadingState.update { LoadingState.Disabled }
            }
        }.launchIn(viewModelScope)
    }
}
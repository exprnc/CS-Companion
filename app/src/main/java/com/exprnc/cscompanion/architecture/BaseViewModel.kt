package com.exprnc.cscompanion.architecture

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel() : ViewModel() {

    private val loadingCount = MutableStateFlow(0)

    private val loadingDataState = MutableStateFlow<LoadingData?>(null)

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Disabled)
    val loadingState = _loadingState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<ViewEvent>()
    val viewEvent = _viewEvent.asSharedFlow()

    private val _viewState = MutableStateFlow<ViewState>(object: ViewState{})
    val viewState = _viewState.asStateFlow()

    init {
        loadingCount.onEach {
            if (it > 0) {
                _loadingState.update { LoadingState.Enabled(loadingDataState.value) }
            } else {
                _loadingState.update { LoadingState.Disabled }
            }
        }.launchIn(viewModelScope)
    }

    abstract fun obtainIntent(intent: Intent)

    protected fun setState(state: ViewState) {
        _viewState.value = state
    }

    protected fun emitEvent(event: ViewEvent) {
        viewModelScope.launch {
            _viewEvent.emit(event)
        }
    }

    protected fun launchCoroutine(
        needLoader: Boolean = false,
        loadingData: LoadingData = LoadingData(),
        block: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            if (needLoader) {
                loadingDataState.update { loadingData }
                loadingCount.update { it + 1 }
            }
            block.invoke()
        }.invokeOnCompletion {
            if (needLoader) {
                loadingCount.update { if (it > 0) it - 1 else 0 }
            }
        }
    }
}
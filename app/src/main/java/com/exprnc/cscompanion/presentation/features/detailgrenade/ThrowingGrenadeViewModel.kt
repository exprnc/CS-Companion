package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.architecture.ViewEvent
import com.exprnc.cscompanion.presentation.navigation.NavigationArgsStore
import dagger.hilt.android.lifecycle.HiltViewModel

class ThrowingGrenadeViewModel : BaseViewModel() {

    private val args: ThrowingGrenadeArgs by lazy { NavigationArgsStore.getArgs(ThrowingGrenadeScreen.ROUTE) }
    private var errorMessage: String? = null

    init {
        setInitialState()
    }

    private fun setInitialState() {
        runCatching {
            args
        }.onSuccess {
            errorMessage = null
            setState(defineState())
        }.onFailure { e ->
            e.printStackTrace()
            errorMessage = e.message
            setState(defineState())
        }
    }

    override fun obtainIntent(intent: Intent) {
        when (intent) {
            is ThrowingGrenadeViewIntent.OnBackPressed -> {
                emitEvent(ViewEvent.PopBackStack())
            }
        }
    }

    private fun defineState() : ThrowingGrenadeViewState {
        errorMessage?.let {
            return ThrowingGrenadeViewState.Error(it)
        }
        return ThrowingGrenadeViewState.DefaultState(args.position)
    }

    override fun onCleared() {
        super.onCleared()
        NavigationArgsStore.removeArgs(ThrowingGrenadeScreen.ROUTE)
    }
}
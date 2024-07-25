package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Position


sealed class ThrowingGrenadeViewState : ViewState {
    data class DefaultState(val position: Position) : ThrowingGrenadeViewState()
    data class Error(val message: String) : ThrowingGrenadeViewState()
}
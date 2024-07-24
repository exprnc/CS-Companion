package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Position


sealed class PositionViewState : ViewState {
    data class DefaultState(val position: Position) : PositionViewState()
    data class Error(val message: String) : PositionViewState()
}
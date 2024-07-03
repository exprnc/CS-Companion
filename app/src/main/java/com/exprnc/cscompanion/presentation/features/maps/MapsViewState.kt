package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Map

sealed class MapsViewState: ViewState {
    object EmptyState : MapsViewState()
    data class Success(val activeMaps: List<Map>, val inactiveMaps: List<Map>) : MapsViewState()
    data class Error(val message: String) : MapsViewState()
}
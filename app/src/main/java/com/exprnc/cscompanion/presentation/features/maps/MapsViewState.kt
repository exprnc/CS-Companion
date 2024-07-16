package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Map

sealed class MapsViewState: ViewState {
    object InitialState: MapsViewState()
    data class Success(val competitiveMaps: List<Map>, val wingmanMaps: List<Map>) : MapsViewState()
    data class Error(val message: String) : MapsViewState()
}
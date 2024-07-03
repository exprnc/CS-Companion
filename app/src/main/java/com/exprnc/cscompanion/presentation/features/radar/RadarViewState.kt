package com.exprnc.cscompanion.presentation.features.radar

import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.presentation.features.maps.MapsViewState

sealed class RadarViewState : ViewState {
    object EmptyState : RadarViewState()
    data class Success(val grenades: List<Grenade>) : RadarViewState()
    data class Error(val message: String) : RadarViewState()
}
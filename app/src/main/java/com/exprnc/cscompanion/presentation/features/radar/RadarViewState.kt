package com.exprnc.cscompanion.presentation.features.radar

import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Map

sealed class RadarViewState : ViewState {
    data class Success(val map: Map, val grenades: List<Grenade>) : RadarViewState()
    data class Error(val message: String) : RadarViewState()
}
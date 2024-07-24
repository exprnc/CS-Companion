package com.exprnc.cscompanion.presentation.features.radar

import androidx.compose.ui.geometry.Offset
import com.exprnc.cscompanion.architecture.ViewState
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.model.Position

sealed class RadarViewState : ViewState {
    data class DefaultState(val map: Map, val grenades: List<Grenade>) : RadarViewState()
    data class GrenadeSelectedState(
        val map: Map,
        val grenade: Grenade,
        val positions: List<Position>
    ) : RadarViewState()
    data class Error(val message: String) : RadarViewState()
}
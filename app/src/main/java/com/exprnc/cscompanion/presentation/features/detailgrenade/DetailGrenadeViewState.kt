package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.presentation.features.maps.MapsViewState


sealed class DetailGrenadeViewState {
    object EmptyState : DetailGrenadeViewState()
    data class Success(val position: Position) : DetailGrenadeViewState()
    data class Error(val message: String) : DetailGrenadeViewState()
}
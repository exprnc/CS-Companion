package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.Intent

sealed interface MapsViewIntent : Intent {
    class OnMapClicked(val mapId: String) : MapsViewIntent
    object OnBackPressed : MapsViewIntent
}
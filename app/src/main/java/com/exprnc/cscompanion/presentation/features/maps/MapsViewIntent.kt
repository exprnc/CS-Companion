package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.Intent

sealed interface MapsViewIntent : Intent {
    object OnMapClicked : MapsViewIntent
    object OnBackPressed : MapsViewIntent
}
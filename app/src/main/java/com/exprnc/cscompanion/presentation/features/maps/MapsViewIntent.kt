package com.exprnc.cscompanion.presentation.features.maps

import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.domain.model.Map

sealed interface MapsViewIntent : Intent {
    class OnMapClicked(val map: Map) : MapsViewIntent
    object OnBackPressed : MapsViewIntent
}
package com.exprnc.cscompanion.presentation.features.radar

import com.exprnc.cscompanion.architecture.Intent


sealed interface RadarViewIntent : Intent {
    object OnGrenadeClicked : RadarViewIntent
    object OnPositionClicked : RadarViewIntent
    object OnBackPressed : RadarViewIntent
}
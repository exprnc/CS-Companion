package com.exprnc.cscompanion.presentation.features.radar

import androidx.compose.ui.geometry.Offset
import com.exprnc.cscompanion.architecture.Intent
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Position


sealed interface RadarViewIntent : Intent {
    class OnGrenadeClicked(val grenade: Grenade) : RadarViewIntent
    class OnPositionClicked(val position: Position) : RadarViewIntent
    object OnBackPressed : RadarViewIntent
}
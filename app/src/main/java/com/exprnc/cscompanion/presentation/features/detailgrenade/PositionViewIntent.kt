package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.Intent

interface PositionViewIntent: Intent {
    object OnBackPressed : PositionViewIntent
}
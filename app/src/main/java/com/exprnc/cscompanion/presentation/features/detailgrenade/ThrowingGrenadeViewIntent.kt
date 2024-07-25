package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.Intent

interface ThrowingGrenadeViewIntent: Intent {
    object OnBackPressed : ThrowingGrenadeViewIntent
}
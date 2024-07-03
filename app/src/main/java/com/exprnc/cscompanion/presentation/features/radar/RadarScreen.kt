package com.exprnc.cscompanion.presentation.features.radar

import androidx.core.os.bundleOf
import com.exprnc.cscompanion.architecture.Screen

class RadarScreen(args: RadarArgs) : Screen(args.toBundle(), ROUTE) {
    companion object {
        const val ROUTE = "radar"
    }
}
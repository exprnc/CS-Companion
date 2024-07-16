package com.exprnc.cscompanion.presentation.ui.components.bottom_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import com.exprnc.cscompanion.presentation.navigation.Graphs.MAPS_GRAPH
import com.exprnc.cscompanion.presentation.navigation.Graphs.SETTINGS_GRAPH

enum class BottomNavItem(val route: String, val label: String) {
    Maps(MAPS_GRAPH, "Maps"),
    Settings(SETTINGS_GRAPH, "Settings")
}
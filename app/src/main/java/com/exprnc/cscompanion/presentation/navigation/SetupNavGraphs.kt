package com.exprnc.cscompanion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.exprnc.cscompanion.presentation.features.maps.MapsGraph
import com.exprnc.cscompanion.presentation.navigation.Graphs.MAPS_GRAPH
import com.exprnc.cscompanion.presentation.ui.components.ScreenViewWrapper
import com.exprnc.cscompanion.presentation.ui.theme.CSCompanionTheme

object Graphs {
    const val MAPS_GRAPH = "MAPS_GRAPH"
    const val SETTINGS_GRAPH = "SETTINGS_GRAPH"
}

@Composable
fun SetupNavGraphs() {
    CSCompanionTheme {
        val navHostController = rememberNavController()
        NavHost(
            navController = navHostController,
            graph = navHostController.createGraph(startDestination = MAPS_GRAPH) {
                composable(MAPS_GRAPH) {
                    ScreenViewWrapper(navHostController = navHostController) {
                        MapsGraph()
                    }
                }
            }
        )
    }
}
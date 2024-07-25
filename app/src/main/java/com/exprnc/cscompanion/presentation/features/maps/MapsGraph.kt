package com.exprnc.cscompanion.presentation.features.maps

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.exprnc.cscompanion.presentation.features.detailgrenade.ThrowingGrenadeScreen
import com.exprnc.cscompanion.presentation.features.detailgrenade.ThrowingGrenadeView
import com.exprnc.cscompanion.presentation.features.radar.RadarScreen
import com.exprnc.cscompanion.presentation.features.radar.RadarView
import com.exprnc.cscompanion.presentation.navigation.Graphs.MAPS_GRAPH

@Composable
fun MapsGraph() {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        graph = navHostController.createGraph(
            startDestination = MapsScreen.ROUTE,
            route = MAPS_GRAPH
        ) {
            composable(MapsScreen.ROUTE) {
                MapsView(navHostController = navHostController)
            }
            composable(RadarScreen.ROUTE) {
                RadarView(navHostController = navHostController)
            }
            composable(ThrowingGrenadeScreen.ROUTE) {
                ThrowingGrenadeView(navHostController = navHostController)
            }
        }
    )
}
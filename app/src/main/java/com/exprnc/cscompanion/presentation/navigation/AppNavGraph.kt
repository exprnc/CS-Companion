package com.exprnc.cscompanion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.exprnc.cscompanion.architecture.AssetParamType
import com.exprnc.cscompanion.presentation.features.maps.MapsScreen
import com.exprnc.cscompanion.presentation.features.maps.MapsView
import com.exprnc.cscompanion.presentation.features.radar.RadarArgs
import com.exprnc.cscompanion.presentation.features.radar.RadarScreen
import com.exprnc.cscompanion.presentation.features.radar.RadarView
import com.exprnc.cscompanion.utils.getParcel

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
) {
    NavHost(navController = navHostController, startDestination = MapsScreen.ROUTE) {
        composable(route = MapsScreen.ROUTE) {
            MapsView(navHostController)
        }
        composable(
            "${RadarScreen.ROUTE}/{${RadarArgs.ARGS_KEY}}",
            arguments = listOf(
                navArgument(RadarArgs.ARGS_KEY) {
                    type = AssetParamType<RadarArgs>()
//                    type = NavType.StringType
                }
            )
        ) {
            val args = requireNotNull(it.arguments?.getParcel<RadarArgs>(RadarArgs.ARGS_KEY))
//            val args = it.arguments?.getString(RadarArgs.ARGS_KEY) ?: -1
            RadarView(args = args, navController = navHostController)
        }
    }
}
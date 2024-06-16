package com.exprnc.cscompanion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.exprnc.cscompanion.ui.theme.CSCompanionTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }

    @Composable
    fun SetupNavGraph(navController: NavHostController) {
        NavHost(navController = navController, startDestination = "mapsScreen") {
            composable("mapsScreen") { MapsScreen(navController) }
            composable("schemeScreen") { SchemeScreen(navController) }
//            composable(
//                "schemeScreen/{mapId}",
//                arguments = listOf(navArgument("mapId") { type = NavType.StringType })
//            ) { backStackEntry ->
//                val mapId = backStackEntry.arguments?.getString("mapId")
//                val mapsViewModel: MapsViewModel = viewModel()
//                val schemeViewModel: SchemeViewModel = viewModel()
//                val map = mapsViewModel.activeMaps.find { it.id == mapId } ?: mapsViewModel.inactiveMaps.find { it.id == mapId }
//                val nades = schemeViewModel.nades.filter { it.mapId == mapId }
//                map?.let {
//                    SchemeScreen(nades, map.scheme)
//                }
//            }
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
}
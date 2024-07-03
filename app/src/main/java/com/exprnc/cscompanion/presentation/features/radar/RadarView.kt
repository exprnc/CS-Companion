package com.exprnc.cscompanion.presentation.features.radar

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.ViewEvent
import com.google.gson.Gson
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun RadarView(
    args: RadarArgs,
    navController: NavHostController,
    viewModel: RadarViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState().value
    val loadingState = viewModel.loadingState.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.viewEvent.onEach {
            when(it) {
                is ViewEvent.PopBackStack -> navController.popBackStack()
                is ViewEvent.Navigation -> navController.navigate(it.screen.getRoute())
            }
        }.launchIn(this)
    }

    Text(text = "RADAR", modifier = Modifier.fillMaxSize())
}
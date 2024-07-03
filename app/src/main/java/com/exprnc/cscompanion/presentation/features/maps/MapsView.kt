package com.exprnc.cscompanion.presentation.features.maps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.ViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun MapsView(
    navController: NavHostController,
    viewModel: MapsViewModel = hiltViewModel()
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
    
    Text(text = "MAPS", modifier = Modifier.fillMaxSize())
    Button(onClick = { viewModel.obtainIntent(MapsViewIntent.OnMapClicked) }, Modifier.padding(30.dp)) {
        Text(text = "КНОПКА")
    }
}
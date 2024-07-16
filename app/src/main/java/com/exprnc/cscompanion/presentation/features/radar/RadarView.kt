package com.exprnc.cscompanion.presentation.features.radar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.ViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun RadarView(
    navHostController: NavHostController,
    viewModel: RadarViewModel = hiltViewModel()
) {
    val state = viewModel.viewState.collectAsState().value
    val loadingState = viewModel.loadingState.collectAsState().value

    LaunchedEffect(key1 = Unit) {
        viewModel.viewEvent.onEach {
            when(it) {
                is ViewEvent.PopBackStack -> navHostController.popBackStack()
                is ViewEvent.Navigation -> navHostController.navigate(it.screen.route)
            }
        }.launchIn(this)
    }

    
    when(state) {
        is RadarViewState.Success -> {
            Column {
                Text(text = "SUCCESS")
//                for(grenade in state.grenades) {
//                    Text(text = grenade.grenadeId)
//                }
            }
        }
        is RadarViewState.Error -> {
            Text(text = "ERROR")
        }
    }
}
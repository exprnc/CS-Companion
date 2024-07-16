package com.exprnc.cscompanion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.architecture.BaseViewModel
import com.exprnc.cscompanion.architecture.Screen
import com.exprnc.cscompanion.architecture.ViewEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun NavHostController.navigateViaRoute(screen: Screen) {
    navigate(screen.route)
}

@Composable
fun ObserveEvents(
    navHostController: NavHostController,
    viewModel: BaseViewModel,
    otherEvents: ((ViewEvent) -> Unit)? = null
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        viewModel.viewEvent.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is ViewEvent.Navigation -> navHostController.navigateViaRoute(it.screen)
                is ViewEvent.NavigationToGraph -> navHostController.navigate(it.route)
                is ViewEvent.PopBackStack -> navHostController.popBackStack()
                else -> otherEvents?.invoke(it)
            }
        }.launchIn(this)
    }
}
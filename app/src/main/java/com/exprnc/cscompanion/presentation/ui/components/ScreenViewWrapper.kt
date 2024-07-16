package com.exprnc.cscompanion.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.exprnc.cscompanion.presentation.ui.components.bottom_navigation.BottomNavigationBar

@Composable
fun ScreenViewWrapper(
    navHostController: NavHostController,
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navHostController = navHostController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content.invoke()
        }
    }
}
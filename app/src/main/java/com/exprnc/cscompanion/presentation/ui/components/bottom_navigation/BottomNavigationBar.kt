package com.exprnc.cscompanion.presentation.ui.components.bottom_navigation

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.exprnc.cscompanion.R

@Composable
fun BottomNavigationBar(navHostController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.entries.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route, onClick = {
                    navHostController.navigate(item.route) {
                        navHostController.graph.startDestinationRoute?.let { popUpTo(it) }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp, 24.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null
                    )
                },
                label = { Text(text = item.label) }
            )
        }
    }
}
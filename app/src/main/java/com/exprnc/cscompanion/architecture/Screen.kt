package com.exprnc.cscompanion.architecture

import com.exprnc.cscompanion.presentation.navigation.NavigationArgsStore

abstract class Screen(
    val route: String,
    args: Args? = null
) {
    init {
        if (args != null) NavigationArgsStore.putArgs(route, args)
    }
}
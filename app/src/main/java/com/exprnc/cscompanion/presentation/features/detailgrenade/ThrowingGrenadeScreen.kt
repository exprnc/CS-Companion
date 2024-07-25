package com.exprnc.cscompanion.presentation.features.detailgrenade

import com.exprnc.cscompanion.architecture.Screen

class ThrowingGrenadeScreen(args: ThrowingGrenadeArgs) : Screen(ROUTE, args) {
    companion object {
        const val ROUTE = "throwing_grenade"
    }
}
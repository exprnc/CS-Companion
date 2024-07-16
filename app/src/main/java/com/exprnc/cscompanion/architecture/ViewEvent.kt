package com.exprnc.cscompanion.architecture

import android.os.Bundle

interface ViewEvent {
    class Navigation(val screen: Screen) : ViewEvent
    class NavigationToGraph(val route: String) : ViewEvent
    class PopBackStack(val bundle: Bundle = Bundle.EMPTY) : ViewEvent
}
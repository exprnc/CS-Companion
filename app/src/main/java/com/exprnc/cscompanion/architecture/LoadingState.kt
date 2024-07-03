package com.exprnc.cscompanion.architecture

sealed class LoadingState {
    object Enabled : LoadingState()
    object Disabled : LoadingState()
}
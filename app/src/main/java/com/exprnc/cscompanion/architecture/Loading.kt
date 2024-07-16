package com.exprnc.cscompanion.architecture

sealed class LoadingState {
    object Enabled : LoadingState()
    object Disabled : LoadingState()
}

data class LoadingData(
    val title: String = "",
    val subtitle: String = "",
    val type: LoadingType = LoadingType.Transparent
)

enum class LoadingType {
    Shimmer,
    Transparent;
}
package com.exprnc.cscompanion.architecture

sealed interface LoadingState {
    class Enabled(val data: LoadingData? = null) : LoadingState
    object Disabled : LoadingState
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
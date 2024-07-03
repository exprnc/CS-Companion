package com.exprnc.cscompanion.domain.model

data class Grenade(
    val grenadeId: String,
    val name: String,
    val side: String,
    val type: String,
    val offsetX: Float,
    val offsetY: Float,
    val mapId: String,
)
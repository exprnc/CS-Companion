package com.exprnc.cscompanion.models

data class Nade(
    val id: String,
    val mapId: String,
    val name: String,
    val offsetX: Float,
    val offsetY: Float,
    val side: String,
    val type: String,
    val positions: List<Position>
)
package com.exprnc.cscompanion.domain.model

data class Position(
    val positionId: String,
    val videoURL: String,
    val offsetX: Float,
    val offsetY: Float,
    val grenadeId: String,
    val mapId: String,
)
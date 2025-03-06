package com.exprnc.cscompanion.domain.model.position

import java.time.LocalDateTime
import java.util.UUID

data class Position(
    val id: UUID,
    val name: String,
    val description: String,
    val video: String,
    val tutorialImages: List<String>,
    val offsetX: Float,
    val offsetY: Float,
    val grenadeId: UUID,
    val isFavorite: Boolean,
    val isCached: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

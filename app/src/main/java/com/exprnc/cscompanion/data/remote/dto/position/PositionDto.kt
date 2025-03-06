package com.exprnc.cscompanion.data.remote.dto.position

import java.time.LocalDateTime
import java.util.UUID

data class PositionDto(
    val id: UUID,
    val name: String,
    val description: String,
    val videoUrl: String,
    val tutorialImageUrls: List<String>,
    val offsetX: Float,
    val offsetY: Float,
    val grenadeId: UUID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

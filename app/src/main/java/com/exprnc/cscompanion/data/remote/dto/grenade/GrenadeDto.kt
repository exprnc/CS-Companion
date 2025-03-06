package com.exprnc.cscompanion.data.remote.dto.grenade

import java.time.LocalDateTime
import java.util.UUID

data class GrenadeDto(
    val id: UUID,
    val name: String,
    val side: String,
    val type: String,
    val offsetX: Float,
    val offsetY: Float,
    val mapId: UUID,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

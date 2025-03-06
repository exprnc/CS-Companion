package com.exprnc.cscompanion.domain.model.grenade

import com.exprnc.cscompanion.domain.model.side.Side
import java.time.LocalDateTime
import java.util.UUID

data class Grenade(
    val id: UUID,
    val name: String,
    val side: Side,
    val type: GrenadeType,
    val offsetX: Float,
    val offsetY: Float,
    val mapId: UUID,
    val isCached: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
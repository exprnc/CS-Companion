package com.exprnc.cscompanion.domain.model.map

import java.time.LocalDateTime
import java.util.UUID

data class Map(
    val id: UUID,
    val name: String,
    val type: MapType,
    val activePool: Boolean,
    val icon: String,
    val image: String,
    val radarImage: String,
    val radarImageWithCallouts: String,
    val isCached: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

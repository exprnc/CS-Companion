package com.exprnc.cscompanion.data.remote.dto.map

import java.time.LocalDateTime
import java.util.UUID

data class MapDto(
    val id: UUID,
    val name: String,
    val type: String,
    val activePool: Boolean,
    val iconUrl: String,
    val imageUrl: String,
    val radarImageUrl: String,
    val radarImageUrlWithCallouts: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
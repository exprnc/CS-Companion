package com.exprnc.cscompanion.data.local.entity.grenade

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "grenades")
data class GrenadeEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val side: String,
    val type: String,
    @ColumnInfo(name = "offset_x") val offsetX: Float,
    @ColumnInfo(name = "offset_y") val offsetY: Float,
    @ColumnInfo(name = "map_id") val mapId: UUID,
    @ColumnInfo(name = "created_at") val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at") val updatedAt: LocalDateTime
)

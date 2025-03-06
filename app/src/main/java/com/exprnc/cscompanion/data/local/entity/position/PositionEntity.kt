package com.exprnc.cscompanion.data.local.entity.position

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "positions")
data class PositionEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val description: String,
    @ColumnInfo(name = "video_path") val videoPath: String,
    @ColumnInfo(name = "tutorial_image_paths") val tutorialImagePaths: List<String>,
    @ColumnInfo(name = "offset_x") val offsetX: Float,
    @ColumnInfo(name = "offset_y") val offsetY: Float,
    @ColumnInfo(name = "grenade_id") val grenadeId: UUID,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "created_at") val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at") val updatedAt: LocalDateTime
)

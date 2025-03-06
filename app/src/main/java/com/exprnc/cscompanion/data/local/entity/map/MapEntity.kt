package com.exprnc.cscompanion.data.local.entity.map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "maps")
data class MapEntity(
    @PrimaryKey val id: UUID,
    val name: String,
    val type: String,
    @ColumnInfo(name = "active_pool") val activePool: Boolean,
    @ColumnInfo(name = "icon_path") val iconPath: String,
    @ColumnInfo(name = "image_path") val imagePath: String,
    @ColumnInfo(name = "radar_image_path") val radarImagePath: String,
    @ColumnInfo(name = "radar_image_path_with_callouts") val radarImagePathWithCallouts: String,
    @ColumnInfo(name = "created_at") val createdAt: LocalDateTime,
    @ColumnInfo(name = "updated_at") val updatedAt: LocalDateTime
)

package com.exprnc.cscompanion.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "positions",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = GrenadeDto::class,
            parentColumns = arrayOf("grenade_id"),
            childColumns = arrayOf("grenade_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class PositionDto(
    @PrimaryKey @ColumnInfo(name = "position_id") val positionId: String,
    @ColumnInfo(name = "video_url") val videoURL: String,
    @ColumnInfo(name = "offsetX") val offsetX: Float,
    @ColumnInfo(name = "offsetY") val offsetY: Float,
    @ColumnInfo(name = "grenade_id") val grenadeId: String,
)
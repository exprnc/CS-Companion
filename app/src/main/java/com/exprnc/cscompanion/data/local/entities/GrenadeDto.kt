package com.exprnc.cscompanion.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlin.collections.Map

@Entity(
    tableName = "grenades",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = MapDto::class,
            parentColumns = arrayOf("map_id"),
            childColumns = arrayOf("map_id"),
            onDelete = ForeignKey.CASCADE
        )
    )
)
data class GrenadeDto(
    @PrimaryKey @ColumnInfo(name = "grenade_id") val grenadeId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "side") val side: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "offsetX") val offsetX: Float,
    @ColumnInfo(name = "offsetY") val offsetY: Float,
    @ColumnInfo(name = "map_id") val mapId: String,
)
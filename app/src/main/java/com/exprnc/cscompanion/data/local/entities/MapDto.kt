package com.exprnc.cscompanion.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maps")
data class MapDto(
    @PrimaryKey @ColumnInfo(name = "map_id") val mapId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: Int,
    @ColumnInfo(name = "active_pool") val activePool: Boolean,
    @ColumnInfo(name = "radar_image") val radarImage: Int,
    @ColumnInfo(name = "radar_image_with_callouts") val radarImageWithCallouts: Int,
)
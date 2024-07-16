package com.exprnc.cscompanion.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maps")
data class MapDto(
    @PrimaryKey @ColumnInfo(name = "map_id") val mapId: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "active_pool") val activePool: Boolean,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "radar_image") val radarImage: String,
    @ColumnInfo(name = "radar_image_with_callouts") val radarImageWithCallouts: String,
)
package com.exprnc.cscompanion.domain.model

data class Map(
    val mapId: String,
    val name: String,
    val image: Int,
    val activePool: Boolean,
    val radarImage: Int,
    val radarImageWithCallouts: Int
)
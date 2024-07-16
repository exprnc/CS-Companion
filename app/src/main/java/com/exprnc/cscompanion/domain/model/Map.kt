package com.exprnc.cscompanion.domain.model

data class Map(
    val mapId: String,
    val name: String,
    val type: String,
    val activePool: Boolean,
    val icon: String,
    val image: String,
    val radarImage: String,
    val radarImageWithCallouts: String
)
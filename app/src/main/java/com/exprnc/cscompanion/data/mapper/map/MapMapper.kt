package com.exprnc.cscompanion.data.mapper.map

import com.exprnc.cscompanion.data.local.entity.map.MapEntity
import com.exprnc.cscompanion.data.remote.dto.map.MapDto
import com.exprnc.cscompanion.domain.model.map.Map

interface MapMapper {
    fun fromDto(dto: MapDto): Map
    fun fromEntity(entity: MapEntity): Map
    fun toEntity(model: Map): MapEntity
}
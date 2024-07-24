package com.exprnc.cscompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.exprnc.cscompanion.data.local.entities.PositionDto

@Dao
interface PositionDao {
    @Query("SELECT * FROM positions WHERE map_id = (:mapId)")
    fun getPositionsByMapId(mapId: String): List<PositionDto>

    @Insert
    fun insert(position: PositionDto)
}
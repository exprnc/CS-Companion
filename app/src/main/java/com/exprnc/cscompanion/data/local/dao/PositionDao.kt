package com.exprnc.cscompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.exprnc.cscompanion.data.local.entities.PositionDto

@Dao
interface PositionDao {
    @Query("SELECT * FROM positions WHERE grenade_id = (:grenadeId)")
    fun getPositionsByGrenadeId(grenadeId: String): List<PositionDto>

    @Insert
    fun insert(position: PositionDto)
}
package com.exprnc.cscompanion.data.local.converter

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {

    @TypeConverter
    fun fromString(value: String): UUID {
        return UUID.fromString(value)
    }

    @TypeConverter
    fun toString(uuid: UUID): String {
        return uuid.toString()
    }
}
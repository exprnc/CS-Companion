package com.exprnc.cscompanion.data.local.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long): LocalDateTime {
        return Instant.ofEpochMilli(value).atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    @TypeConverter
    fun toTimestamp(date: LocalDateTime): Long {
        return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}
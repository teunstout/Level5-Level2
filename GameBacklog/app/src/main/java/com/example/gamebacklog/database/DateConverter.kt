package com.example.gamebacklog.database


import androidx.room.TypeConverter
import java.util.*

class DateConverter{
    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun longToDate(longValue: Long?): Date? {
        return longValue?.let { Date(it) } // value == null ? null : new Date(value);
    }
}
package com.example.capstoneshopping.Converters

import androidx.room.TypeConverter
import java.util.*

class Converters {
    //Called upon when needing to convert a Date to type of Date the ROOM database
    //can understand and store.
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
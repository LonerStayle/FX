package kr.grandoption.fx.db.entity

import androidx.room.TypeConverter
import java.util.*

class GameModelDateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
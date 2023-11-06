package dev.passerby.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class JsonConverters {
    @TypeConverter
    fun intListToJson(value: List<Int>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToIntList(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}
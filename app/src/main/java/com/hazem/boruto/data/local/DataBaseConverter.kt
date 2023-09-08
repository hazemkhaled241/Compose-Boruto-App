package com.hazem.boruto.data.local

import androidx.room.TypeConverter

class DataBaseConverter {
    @TypeConverter
    fun convertListToString(list: List<String>): String {
    return list.joinToString()
    }
    @TypeConverter
    fun convertStringToList(string: String): List<String> {
    return string.split(",")
    }
}
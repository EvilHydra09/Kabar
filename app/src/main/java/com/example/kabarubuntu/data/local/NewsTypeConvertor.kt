package com.example.kabarubuntu.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.kabarubuntu.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(name: String): Source {
        val list = name.split(",")
        return Source(list[0], list[1])
    }


}
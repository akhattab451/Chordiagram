package com.akhttp.chordiagram.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun toModifier(value: String) = enumValueOf<ChordModifier>(value)

    @TypeConverter
    fun fromModifier(value: ChordModifier) = value.name
}
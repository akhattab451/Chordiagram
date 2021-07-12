package com.example.chordiagram.data

import androidx.room.TypeConverter

class ModifierConverter {
    @TypeConverter
    fun toModifier(value: String) = enumValueOf<ChordModifier>(value)

    @TypeConverter
    fun fromModifier(value: ChordModifier) = value.name
}
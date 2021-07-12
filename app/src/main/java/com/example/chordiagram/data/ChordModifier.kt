package com.example.chordiagram.data

enum class ChordModifier(val value: Int) {
    MAJOR(10),
    MINOR(11),
    SEVEN(12),
    DIMINISHED(13),
    AUGMENTED(14),
    SIX(15),
    NINE(16);


    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
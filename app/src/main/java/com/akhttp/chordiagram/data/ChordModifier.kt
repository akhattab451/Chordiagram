package com.akhttp.chordiagram.data

enum class ChordModifier(val value: Int) {
    MAJOR(10),
    MINOR(11),
    SIX(12),
    SEVEN(13),
    NINE(14),
    DIMINISHED(15),
    AUGMENTED(16),
    SUSPENDED(17);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}
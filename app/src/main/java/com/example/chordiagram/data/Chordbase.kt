package com.example.chordiagram.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Chord::class], version = 5, exportSchema = false)
@TypeConverters(ModifierConverter::class)
abstract class Chordbase : RoomDatabase() {
    abstract val chordDao: ChordDao

}
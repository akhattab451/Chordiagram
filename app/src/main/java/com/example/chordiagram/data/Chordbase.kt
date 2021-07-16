package com.example.chordiagram.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Chord::class], version = 6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Chordbase : RoomDatabase() {

    abstract val chordDao: ChordDao

}
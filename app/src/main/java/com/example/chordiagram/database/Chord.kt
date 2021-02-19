package com.example.chordiagram.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chords")
data class Chord(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "chord_name") val chordName : String,
    @ColumnInfo(name = "resource_name") val resourceName : String)
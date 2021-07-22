package com.akhttp.chordiagram.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chords")
data class Chord(
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo(name = "chord_name") val chordName : String,
    @ColumnInfo(name = "other_name") val otherName : String?,
    @ColumnInfo(name = "chord_modifier") val chordModifier: ChordModifier,
    @ColumnInfo(name = "resource_name") val resourceName : String,
    //@ColumnInfo(name = "starting_position") val startingPosition : Int,
    //val notes: List<Note>
    )



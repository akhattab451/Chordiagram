package com.example.chordiagram.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ChordDao {

    @Query("SELECT * FROM chords")
    suspend fun getAllChords() : List<Chord>

}
package com.example.chordiagram.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface ChordDao {

    @Query("SELECT * FROM chords")
    suspend fun getAllChords() : List<Chord>

    @Query("SELECT * FROM chords WHERE chord_name IN (:chords)")
    suspend fun getRequestedChords(chords : List<String>) : List<Chord>
}
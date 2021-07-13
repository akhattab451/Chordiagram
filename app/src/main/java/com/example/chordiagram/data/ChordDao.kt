package com.example.chordiagram.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChordDao {

    @Query("SELECT * FROM chords")
    fun getAllChords() : Flow<List<Chord>>

    @Query("SELECT * FROM chords WHERE chord_name IN (:chords)")
    fun getConvertChords(chords : List<String>) : Flow<List<Chord>>
}
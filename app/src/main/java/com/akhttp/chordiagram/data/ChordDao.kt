package com.akhttp.chordiagram.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChordDao {

    @Query("SELECT * FROM chords ORDER BY id ASC")
    fun getAllChords() : PagingSource<Int, Chord>

    @Query("SELECT * FROM chords WHERE chord_name IN (:chords) OR other_name IN (:chords)")
    fun getConvertChords(chords : List<String>) : Flow<List<Chord>>
}
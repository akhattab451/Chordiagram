package com.example.chordiagram.data

import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChordRepository @Inject constructor(private val chordDao: ChordDao) {

    fun getAllChords() = chordDao.getAllChords()

    fun getFilteredChords(
        filter: (Chord) -> Boolean
    ) = chordDao.getAllChords().map { chords ->
        chords.filter {
            filter(it)
        }
    }

    fun getConvertChords(chords: List<String>) = chordDao.getConvertChords(chords)
}
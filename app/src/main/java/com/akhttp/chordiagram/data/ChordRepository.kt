package com.akhttp.chordiagram.data

import androidx.paging.*
import javax.inject.Inject

class ChordRepository @Inject constructor(private val dao: ChordDao) {

    val allPagedChords = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            dao.getAllChords()
        }
    ).flow

    fun getConvertChords(chords: List<String>) = dao.getConvertChords(chords)
}
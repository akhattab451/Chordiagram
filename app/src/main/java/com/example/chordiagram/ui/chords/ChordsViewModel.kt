package com.example.chordiagram.ui.chords

import androidx.lifecycle.*
import com.example.chordiagram.data.ChordModifier
import com.example.chordiagram.data.ChordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChordsViewModel @Inject constructor(
    private val repository: ChordRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val requestType = savedStateHandle.getLiveData(REQUEST_TYPE, SHOW_ALL)

    val chords = requestType.switchMap {
        when (it) {
            SHOW_ALL -> repository.getAllChords()
            CONVERT -> {
                val chordString = savedStateHandle.get<String>(CHORD_STRING_TAG)!!
                val chordList = splitToChords(chordString)
                repository.getConvertChords(chordList)
            }
            SEARCH -> {
                val query = savedStateHandle.get<String>(QUERY_TAG)!!
                repository.getFilteredChords { chord ->
                    chord.chordName.contains(query, true)
                }
            }
            else -> repository.getFilteredChords { chord ->
                chord.chordModifier == ChordModifier.fromInt(it)
            }
        }.asLiveData()
    }

    val empty = Transformations.map(chords) {
        it.isNullOrEmpty()
    }

    private fun splitToChords(chords: String): List<String> {
        return chords.split(" ").toMutableList().map { chordName ->
            chordName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
    }

    fun setFilter(filter: Int) {
        savedStateHandle.set(REQUEST_TYPE, filter)
    }

    fun searchChords(query: String) {
        savedStateHandle.set(QUERY_TAG, query)
        setFilter(SEARCH)
    }

    companion object {
        const val REQUEST_TYPE = "requestType"
        const val CHORD_STRING_TAG = "chordString"
        const val QUERY_TAG = "query"
        const val SHOW_ALL = 0
        const val SEARCH = 1
        const val CONVERT = 2
    }
}
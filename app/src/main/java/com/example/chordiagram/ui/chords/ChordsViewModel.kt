package com.example.chordiagram.ui.chords

import androidx.lifecycle.*
import com.example.chordiagram.data.Chord
import com.example.chordiagram.data.ChordDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChordsViewModel @Inject constructor(
    private val chordDao: ChordDao,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _chords = MutableLiveData<List<Chord>>()
    val chords: LiveData<List<Chord>>
        get() = _chords

    val empty = Transformations.map(_chords) {
        it.isNullOrEmpty()
    }

    fun getFilteredChords(filter: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _chords.postValue(
                getChords { true }!!
            )
        }
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _chords.postValue(
                getChords { it.chordName.contains(query, true) }!!
            )
        }
    }

    private suspend fun getChords(condition: (Chord) -> Boolean) = chordDao.getAllChords().filter { chord ->
        condition(chord)
    }

    fun getRequestedChords(chordString: String) {
        val chordsList = splitToChords(chordString)
        viewModelScope.launch(Dispatchers.IO) {
            _chords.postValue(
                chordDao.getRequestedChords(chordsList).sortedWith { chord1, chord2 ->
                    chordsList.indexOf(chord1.chordName) - chordsList.indexOf(chord2.chordName)
                }
            )
        }
    }

    private fun splitToChords(chords: String): List<String> {
        return chords.split(" ").toMutableList().map { chordName ->
            chordName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
    }

    companion object {
        const val FILTER_KEY = "FILTER"
        const val SHOW_ALL = 0
    }
}
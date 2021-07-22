package com.akhttp.chordiagram.ui.requested

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.akhttp.chordiagram.data.ChordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RequestedViewModel @Inject constructor(
    repository: ChordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val query = savedStateHandle.getLiveData<String>("chordString")
    val requestedChords = query.switchMap {
        val chordList = splitToChords(it)
        repository.getConvertChords(chordList).map { chords ->
            chords.sortedWith { chord1, chord2 ->
                chordList.indexOf(chord1.chordName) - chordList.indexOf(chord2.chordName)
            }
        }.asLiveData()
    }

    private fun splitToChords(chords: String): List<String> {
        return chords.split(" ").toMutableList().map { chordName ->
            chordName.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }
    }

}
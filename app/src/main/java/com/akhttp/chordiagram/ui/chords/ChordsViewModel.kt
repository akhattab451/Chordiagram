package com.akhttp.chordiagram.ui.chords

import androidx.lifecycle.*
import androidx.paging.cachedIn
import androidx.paging.filter
import com.akhttp.chordiagram.data.ChordModifier
import com.akhttp.chordiagram.data.ChordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChordsViewModel @Inject constructor(
    repository: ChordRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val requestType = savedStateHandle.getLiveData(REQUEST_TYPE, SHOW_ALL)
    private val _chords = repository.allPagedChords.asLiveData().cachedIn(viewModelScope)

    val chords = requestType.switchMap { requestType ->
        _chords.map { pagingData ->
            pagingData.filter { chord ->
                when (requestType) {
                    SHOW_ALL -> true
                    SEARCH -> {
                        val query = savedStateHandle.get<String>(QUERY_TAG)!!
                        chord.chordName.contains(query, true) ||
                                chord.otherName?.contains(query, true) == true
                    }
                    else -> chord.chordModifier == ChordModifier.fromInt(requestType)
                }
            }
        }
    }

    fun setRequestType(filter: Int) {
        savedStateHandle.set(REQUEST_TYPE, filter)
    }

    fun searchChords(query: String) {
        savedStateHandle.set(QUERY_TAG, query)
        setRequestType(SEARCH)
    }

    companion object {
        const val REQUEST_TYPE = "requestType"
        const val QUERY_TAG = "query"
        const val SHOW_ALL = 0
        const val SEARCH = 1
    }
}
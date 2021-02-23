package com.example.chordiagram.ui.chords

import android.app.Application
import androidx.lifecycle.*
import com.example.chordiagram.Utils
import com.example.chordiagram.database.Chord
import com.example.chordiagram.database.Chordbase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ChordsViewModel(private val app: Application, private val chordsString: String) :
    ViewModel() {

    private val _chords = MutableLiveData<List<Chord>>()
    val chords: LiveData<List<Chord>>
        get() = _chords

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean>
        get() = _empty

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        _loading.value = true
        viewModelScope.launch {
            _chords.value = Chordbase.getInstance(app.applicationContext).chordDao.let {
                if (chordsString.isEmpty())
                    it.getAllChords()
                else
                    it.getRequestedChords(Utils.splitToChords(chordsString))
            }
            _loading.value = false
            _empty.value = _chords.value.isNullOrEmpty()
        }
    }
}


class ChordsViewModelFactory(private val app: Application, private val chordsString: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ChordsViewModel::class.java))
            return ChordsViewModel(app, chordsString) as T

        throw IllegalArgumentException("Unrecognized ViewModel")
    }
}
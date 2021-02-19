package com.example.chordiagram.ui.chords

import android.app.Application
import androidx.lifecycle.*
import com.example.chordiagram.database.Chord
import com.example.chordiagram.database.Chordbase
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ChordsViewModel(private val app: Application, private val chordsString: String) :
    ViewModel() {

    private val _chords = MutableLiveData<List<Chord>>()
    val chords: LiveData<List<Chord>>
        get() = _chords

    val requestString = chordsString

    init {
        viewModelScope.launch {

            _chords.value = Chordbase.getInstance(app.applicationContext).chordDao.let {
                if (chordsString.isEmpty())
                    it.getAllChords()
                else
                    it.getRequestedChords(chordsString.split(" ").toList())

            }

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
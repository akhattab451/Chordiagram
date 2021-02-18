package com.example.chordiagram.ui.chords

import android.app.Application
import androidx.lifecycle.*
import com.example.chordiagram.database.Chord
import com.example.chordiagram.database.Chordbase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class ChordsViewModel (private val app: Application) : ViewModel() {

    private val _chords = MutableLiveData<List<Chord>>()
    val chords : LiveData<List<Chord>>
        get() = _chords

    init {

        viewModelScope.launch {
            _chords.value = Chordbase.getInstance(app.applicationContext).chordDao.getAllChords()
        }
    }
}

class ChordsViewModelFactory (private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ChordsViewModel::class.java))
            return ChordsViewModel(app) as T

        throw IllegalArgumentException("Unrecognized ViewModel")
    }
}
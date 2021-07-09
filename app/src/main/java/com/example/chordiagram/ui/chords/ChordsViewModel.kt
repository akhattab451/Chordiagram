package com.example.chordiagram.ui.chords

import android.app.Application
import androidx.lifecycle.*
import com.example.chordiagram.database.Chord
import com.example.chordiagram.database.Chordbase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*

class ChordsViewModel(private val app: Application, private val chordsString: String) :
    ViewModel() {

    private val chordsList = splitToChords(chordsString)

    private val _chords = MutableLiveData<List<Chord>>()
    val chords = Transformations.map(_chords) {
        it.sortedWith { chord1, chord2 ->
            chordsList.indexOf(chord1.chordName) - chordsList.indexOf(chord2.chordName)
        }
    }

     val empty = Transformations.map(_chords) {
         it.isNullOrEmpty()
     }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    init {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            _chords.postValue(Chordbase.getInstance(app.applicationContext).chordDao.let {
                if (chordsString.isEmpty())
                    it.getAllChords()
                else {
                    it.getRequestedChords(chordsList)
                }
            })
            _loading.postValue(false)
        }
    }

    private fun splitToChords(chords : String) : List<String> {
        return chords.split(" ").toMutableList().map {
            it.capitalize(Locale.ROOT)
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
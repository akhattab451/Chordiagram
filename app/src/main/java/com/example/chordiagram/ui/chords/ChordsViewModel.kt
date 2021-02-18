package com.example.chordiagram.ui.chords

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chordiagram.database.Chord

class ChordsViewModel : ViewModel() {

    private val _chords = MutableLiveData<List<Chord>>()
    val chords : LiveData<List<Chord>>
        get() = _chords

    init {
    }
}
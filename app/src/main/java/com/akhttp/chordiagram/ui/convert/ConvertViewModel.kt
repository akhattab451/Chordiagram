package com.akhttp.chordiagram.ui.convert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConvertViewModel @Inject constructor() : ViewModel() {
    private val _eventNavigateToChords = MutableLiveData<String>()
    val eventNavigateToChords: LiveData<String>
        get() = _eventNavigateToChords

    fun navigateToChords(chords: String) {
        _eventNavigateToChords.value = chords
    }

    fun navigateToChordsCompleted() {
        _eventNavigateToChords.value = ""
    }
}
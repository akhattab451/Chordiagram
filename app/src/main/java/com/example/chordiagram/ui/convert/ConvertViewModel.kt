package com.example.chordiagram.ui.convert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConvertViewModel : ViewModel() {

    private val _eventNavigateToChords = MutableLiveData<Boolean>()
    val eventNavigateToChords : LiveData<Boolean>
        get() = _eventNavigateToChords

    fun navigateToChords() {
        _eventNavigateToChords.value = true
    }

    fun navigateToChordsCompleted() {
        _eventNavigateToChords.value = false
    }



}
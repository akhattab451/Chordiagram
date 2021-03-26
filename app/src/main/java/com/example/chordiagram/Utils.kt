package com.example.chordiagram

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import java.util.*

class Utils private constructor(){
    companion object {
        @SuppressLint("ServiceCast")
        fun hideKeyboard(activity: Activity) {
            val inputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            val currentFocusedView = activity.currentFocus
            currentFocusedView?.let {
                inputMethodManager.hideSoftInputFromWindow(
                    currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

        fun splitToChords(chords : String) : List<String> {

            return chords.split(" ").toMutableList().map {
                it.capitalize(Locale.ROOT)
            }
        }
    }

}
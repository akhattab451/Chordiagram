package com.example.chordiagram

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chordiagram.database.Chord
import com.example.chordiagram.ui.chords.ChordsAdapter

@BindingAdapter("chordSrc")
fun setChordForImage(imgView : ImageView, chord : Chord?) {
    val context = imgView.context
    val resId : Int

    chord?.let {
        resId = context.resources.getIdentifier(it.resourceName, "drawable", context.packageName)
        imgView.setImageResource(resId)
    }
}

@BindingAdapter("chordList")
fun setChordList(recyclerView: RecyclerView, chords : List<Chord>?) {
    val adapter = recyclerView.adapter as ChordsAdapter
    adapter.submitList(chords)
}
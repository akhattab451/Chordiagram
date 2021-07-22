package com.akhttp.chordiagram.adapter

import androidx.recyclerview.widget.RecyclerView
import com.akhttp.chordiagram.R
import com.akhttp.chordiagram.data.Chord
import com.akhttp.chordiagram.databinding.ChordListItemBinding

class ChordViewHolder(private val binding : ChordListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(chord : Chord) {
        val context = binding.root.context

        with(chord) {
            val resId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)

            binding.chordImage.setImageResource(
                if(resId != 0) resId
                else (R.drawable.unknown_chord)
            )

            binding.chordName.text = chordName.apply {
                if(otherName != null)
                    "$this/$otherName"
            }
        }
    }

}
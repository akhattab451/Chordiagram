package com.example.chordiagram.ui.chords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chordiagram.R
import com.example.chordiagram.data.Chord
import com.example.chordiagram.databinding.ChordListItemBinding


class ChordsAdapter : ListAdapter<Chord, ChordsAdapter.ChordViewHolder>(ListItemCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChordViewHolder(ChordListItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ChordViewHolder, position: Int) {
        val chord = getItem(position)
        holder.bind(chord)
    }

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
                    otherName?.let {
                        "$this/$it"
                    }
                }
            }
        }

    }

    class ListItemCallbacks : DiffUtil.ItemCallback<Chord>() {
        override fun areItemsTheSame(oldItem: Chord, newItem: Chord) : Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chord, newItem: Chord) : Boolean {
            return oldItem == newItem
        }
    }

}
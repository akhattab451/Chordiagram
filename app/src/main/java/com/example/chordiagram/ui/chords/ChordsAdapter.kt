package com.example.chordiagram.ui.chords

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
            val context = binding.chordImageView.context
            val resId = context.resources.getIdentifier(/*chord.resourceName*/"ic_a", "drawable", context.packageName)
            binding.chordImageView.setImageResource(resId)
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
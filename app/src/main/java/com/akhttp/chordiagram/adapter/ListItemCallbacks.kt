package com.akhttp.chordiagram.adapter

import androidx.recyclerview.widget.DiffUtil
import com.akhttp.chordiagram.data.Chord

object ListItemCallbacks : DiffUtil.ItemCallback<Chord>() {
    override fun areItemsTheSame(oldItem: Chord, newItem: Chord) : Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Chord, newItem: Chord) : Boolean {
        return oldItem == newItem
    }
}
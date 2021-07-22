package com.akhttp.chordiagram.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.akhttp.chordiagram.data.Chord
import com.akhttp.chordiagram.databinding.ChordListItemBinding

class ChordsAdapter : ListAdapter<Chord, ChordViewHolder>(ListItemCallbacks) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChordViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChordViewHolder(ChordListItemBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ChordViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}
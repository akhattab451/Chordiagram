package com.example.chordiagram.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chordiagram.ui.chords.ChordsFragment
import com.example.chordiagram.ui.convert.ConvertFragment

class SectionsStateAdapter(fm : FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fm , lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> ConvertFragment.getInstance()
            else -> ChordsFragment.getInstance()
        }

    }

}
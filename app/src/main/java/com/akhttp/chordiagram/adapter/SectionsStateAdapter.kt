package com.akhttp.chordiagram.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akhttp.chordiagram.ui.chords.ChordsFragment
import com.akhttp.chordiagram.ui.convert.ConvertFragment

class SectionsStateAdapter(fragment: Fragment)
    : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position) {
            0 -> ConvertFragment.newInstance()
            else -> ChordsFragment.newInstance()
        }

    }

}
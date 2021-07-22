package com.akhttp.chordiagram.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.akhttp.chordiagram.R
import com.akhttp.chordiagram.databinding.TabsFragmentBinding
import com.akhttp.chordiagram.adapter.SectionsStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TabsFragment : Fragment(R.layout.tabs_fragment) {
    private val titles = arrayOf(
        R.string.converter_tab_text,
        R.string.chords_tab_text)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = TabsFragmentBinding.bind(view)
        val sectionsStateAdapter = SectionsStateAdapter(this)
        binding.viewPager.adapter = sectionsStateAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(titles[position])
        }.attach()
    }
}
package com.example.chordiagram.ui.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chordiagram.R
import com.example.chordiagram.databinding.TabsFragmentBinding
import com.example.chordiagram.ui.SectionsStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TabsFragment : Fragment() {
    private val titles = arrayOf(
        R.string.converter_tab_text,
        R.string.chords_tab_text)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            val binding = TabsFragmentBinding.inflate(inflater)

            val sectionsStateAdapter = SectionsStateAdapter(childFragmentManager, lifecycle)
            binding.viewPager.adapter = sectionsStateAdapter

            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
                tab.text = getString(titles[position])
            }.attach()

        return binding.root
    }
}
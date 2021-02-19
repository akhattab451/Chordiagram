package com.example.chordiagram

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chordiagram.databinding.ActivityMainBinding
import com.example.chordiagram.ui.SectionsStateAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val titles = arrayOf(
        R.string.converter_tab_text,
        R.string.chords_tab_text
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val sectionsStateAdapter = SectionsStateAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = sectionsStateAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(titles[position])
        }.attach()

    }


}



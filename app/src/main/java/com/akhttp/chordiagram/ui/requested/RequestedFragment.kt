package com.akhttp.chordiagram.ui.requested

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akhttp.chordiagram.R
import com.akhttp.chordiagram.adapter.ChordsAdapter
import com.akhttp.chordiagram.databinding.ChordsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestedFragment : Fragment(R.layout.chords_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: RequestedViewModel by viewModels()
        val binding = ChordsFragmentBinding.bind(view)

        val adapter = ChordsAdapter()
        binding.chordList.adapter = adapter

        viewModel.requestedChords.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}
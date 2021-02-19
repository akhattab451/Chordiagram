package com.example.chordiagram.ui.chords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chordiagram.databinding.ChordsFragmentBinding
import com.example.chordiagram.ui.chords.ChordsFragmentArgs as ChordsFragmentArgs


class ChordsFragment : Fragment() {

    companion object {
        fun newInstance() : ChordsFragment {
            return ChordsFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = ChordsFragmentBinding.inflate(inflater)
        val application = requireNotNull(activity).application

        val text = arguments?.let { ChordsFragmentArgs.fromBundle(it) }?.chordString

        val viewModelFactory = ChordsViewModelFactory(application, text ?: "")
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ChordsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.chordList.adapter = ChordsAdapter()

        return binding.root
    }



}
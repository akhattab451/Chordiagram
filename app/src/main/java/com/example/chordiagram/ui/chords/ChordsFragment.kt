package com.example.chordiagram.ui.chords

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chordiagram.R
import com.example.chordiagram.databinding.ChordsFragmentBinding

class ChordsFragment : Fragment() {

    companion object {
        fun newInstance() = ChordsFragment()
    }

    private lateinit var viewModel: ChordsViewModel
    private lateinit var binding: ChordsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChordsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChordsViewModel::class.java)
    }

}
package com.example.chordiagram.ui.chords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chordiagram.R
import com.example.chordiagram.databinding.ChordsFragmentBinding


class ChordsFragment : Fragment() {

    companion object {
        private var INSTANCE : ChordsFragment? = null

        fun getInstance() : ChordsFragment {

            if (INSTANCE == null)
                INSTANCE = ChordsFragment()

            return INSTANCE!!
        }
    }

    private lateinit var viewModel: ChordsViewModel
    private lateinit var binding: ChordsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChordsFragmentBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(ChordsViewModel::class.java)




        return binding.root
    }

}
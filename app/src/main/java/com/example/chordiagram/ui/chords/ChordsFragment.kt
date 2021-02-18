package com.example.chordiagram.ui.chords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chordiagram.R
import com.example.chordiagram.database.Chordbase
import com.example.chordiagram.databinding.ChordsFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChordsFragment : Fragment() {

    companion object {
        private var INSTANCE : ChordsFragment? = null

        fun getInstance() : ChordsFragment {

            if (INSTANCE == null)
                INSTANCE = ChordsFragment()

            return INSTANCE!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = ChordsFragmentBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        val viewModelFactory = ChordsViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ChordsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.chordList.adapter = ChordsAdapter()

        return binding.root
    }

}
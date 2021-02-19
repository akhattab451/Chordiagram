package com.example.chordiagram.ui.convert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chordiagram.NavigationDirections
import com.example.chordiagram.R
import com.example.chordiagram.databinding.ConvertFragmentBinding


class ConvertFragment : Fragment() {

    companion object {
        fun newInstance() : ConvertFragment {
            return ConvertFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = ConvertFragmentBinding.inflate(inflater)
        val convertViewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)

        binding.lifecycleOwner = this

        binding.convertFab.setOnClickListener {
            convertViewModel.navigateToChords()
        }

        convertViewModel.eventNavigateToChords.observe(viewLifecycleOwner, {
            if (it) {
                val text = binding.convertEditText.text.toString()

                if (text.isNotBlank())
                    findNavController().navigate(NavigationDirections.actionGlobalChordsFragment(text))

                convertViewModel.navigateToChordsCompleted()
            }
        })

        return binding.root
    }

}


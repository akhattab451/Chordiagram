package com.example.chordiagram.ui.convert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chordiagram.NavigationDirections
import com.example.chordiagram.R
import com.example.chordiagram.Utils
import com.example.chordiagram.databinding.ConvertFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConvertFragment : Fragment(R.layout.convert_fragment) {

    companion object {
        fun newInstance() : ConvertFragment {
            return ConvertFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ConvertFragmentBinding.bind(view)
        val convertViewModel: ConvertViewModel by viewModels()

        binding.convertFab.setOnClickListener {
            val text = binding.convertEditText.text.toString()
            convertViewModel.navigateToChords(text)
        }

        convertViewModel.eventNavigateToChords.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank()) {
                findNavController().navigate(NavigationDirections.actionGlobalChordsFragment(it))
                Utils.hideKeyboard(requireActivity())
                convertViewModel.navigateToChordsCompleted()
            }
        })

    }
}


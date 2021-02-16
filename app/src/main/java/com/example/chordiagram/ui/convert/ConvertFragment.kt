package com.example.chordiagram.ui.convert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.chordiagram.R
import com.example.chordiagram.databinding.ConverterFragmentBinding

class ConvertFragment : Fragment() {

    companion object {
        fun newInstance() = ConvertFragment()
    }

    private lateinit var convertViewModel: ConvertViewModel
    private lateinit var binding: ConverterFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = ConverterFragmentBinding.inflate(inflater)

        return binding.root
    }

}
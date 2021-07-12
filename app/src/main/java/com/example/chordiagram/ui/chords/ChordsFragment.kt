package com.example.chordiagram.ui.chords

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.chordiagram.R
import com.example.chordiagram.Utils
import com.example.chordiagram.data.ChordModifier
import com.example.chordiagram.databinding.ChordsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChordsFragment : Fragment(R.layout.chords_fragment) {
    companion object {
        fun newInstance(): ChordsFragment {
            return ChordsFragment()
        }
    }

    private val viewModel: ChordsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = ChordsFragmentBinding.bind(view)

        arguments?.getString("chordString").let {
            if (it.isNullOrBlank()) {
                setHasOptionsMenu(true)
                viewModel.getFilteredChords(0)
            }
            else
                viewModel.getRequestedChords(it)
        }

        val adapter = ChordsAdapter()
        binding.chordList.adapter = adapter

        viewModel.chords.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.empty.observe(viewLifecycleOwner){
            binding.emptyView.isVisible = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.chords_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        (searchItem.actionView as SearchView).apply {
            queryHint = getString(R.string.search) + "... "

            setOnSearchClickListener {
                menu.findItem(R.id.action_filter).isVisible = false
            }

            setOnCloseListener {
                Utils.hideKeyboard(requireActivity())
                activity?.invalidateOptionsMenu()
                return@setOnCloseListener true
            }

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    if (query == null)
                        return false

                    viewModel.getSearchResults(query)
                    return true
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.getFilteredChords(
            when(item.itemId) {
                R.id.action_show_major -> ChordModifier.MAJOR.value
                R.id.action_show_minor -> ChordModifier.MINOR.value
                R.id.action_show_seven -> ChordModifier.SEVEN.value
                R.id.action_show_diminished -> ChordModifier.DIMINISHED.value
                R.id.action_show_augmented -> ChordModifier.AUGMENTED.value
                R.id.action_show_six -> ChordModifier.SIX.value
                R.id.action_show_nine -> ChordModifier.NINE.value
                else -> ChordsViewModel.SHOW_ALL
            }
        )
        return super.onOptionsItemSelected(item)
    }
}
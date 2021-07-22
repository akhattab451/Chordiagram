package com.akhttp.chordiagram.ui.chords

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import com.akhttp.chordiagram.R
import com.akhttp.chordiagram.adapter.ChordsPagingAdapter
import com.akhttp.chordiagram.databinding.ChordsFragmentBinding
import com.akhttp.chordiagram.util.Utils
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

//        arguments?.getString(CHORD_STRING_TAG).let {
//            if (it.isNullOrBlank()) {
//                setHasOptionsMenu(true)
//                setFilter(SHOW_ALL)
//            } else
//                setFilter(CONVERT)
//        }

        val adapter = ChordsPagingAdapter()
        adapter.addLoadStateListener {
            val state = it.source.refresh
            binding.loadingIndicator.isVisible = state is LoadState.Loading
            binding.emptyView.isVisible = state is LoadState.NotLoading
                    && it.append.endOfPaginationReached
                    && adapter.itemCount < 1
        }
        binding.chordList.adapter = adapter

        viewModel.chords.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        setHasOptionsMenu(true)
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
                    viewModel.searchChords(query)
                    return true
                }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_filter)
            createDialog()

        return super.onOptionsItemSelected(item)
    }

    private fun createDialog() {
        val filterArray: Array<String> = resources.getStringArray(R.array.filter_strings)
        val filterArrayId: IntArray = resources.getIntArray(R.array.filter_ids)

        return AlertDialog.Builder(context).setItems(filterArray) { dialog, which ->
            viewModel.setRequestType(filterArrayId[which])
            dialog.dismiss()
        }.create().show()

    }
}
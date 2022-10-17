package com.devsaul.bookfinderapp.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.FragmentSearchBinding
import com.devsaul.bookfinderapp.ui.home.adapters.BooksAdapter
import com.devsaul.bookfinderapp.ui.home.viewmodel.FavViewModel
import com.devsaul.bookfinderapp.ui.home.viewmodel.SearchViewModel
import com.devsaul.bookfinderapp.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val favViewModel by viewModels<FavViewModel>()
    private var searchByAutor: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        manageEvents()
    }

    private fun manageEvents() {
        binding.fbSearch.setOnClickListener {
            if (validateInput()) {
                binding.searchTextField.error = getString(R.string.MSG_SEARCH_ERRO)
            } else {
                binding.searchTextField.error = null
                setSetOnTop()
                hiddenKeyboard(binding.searchEditText.getWindowToken(), requireActivity())
                binding.divider.show()
                binding.loadingLayout.show()
                binding.RecyclerListTickets.show()
                validateSeach()
            }
        }
        binding.btnFav.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_favFragment)
        }
        viewModel.books.observe(viewLifecycleOwner) {
            binding.loadingLayout.hide()
            binding.RecyclerListTickets.adapter = BooksAdapter(it.toMutableList(), this@SearchFragment,favViewModel)
        }
        binding.ChipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chip_1 -> {
                    searchByAutor = false
                }
                R.id.chip_2 -> {
                    searchByAutor = true
                }
            }
        }
    }

    private fun validateSeach() {
        if (searchByAutor) {
            viewModel.getBookByAuthor(binding.searchEditText.text.toString())
        } else {
            viewModel.getBookByTitle(binding.searchEditText.text.toString())
        }
    }

    private fun validateInput(): Boolean {
        return binding.searchEditText.text.isNullOrEmpty()
    }

    private fun setUpView() {

        binding.RecyclerListTickets.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = BooksAdapter(mutableListOf(), this@SearchFragment,favViewModel)
        }

        setSetOnCenter()

    }


    private fun setSetOnTop() {
        val params = binding.constraintSearch.getLayoutParams() as RelativeLayout.LayoutParams
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        binding.constraintSearch.setLayoutParams(params)
    }

    private fun setSetOnCenter() {
        val params = binding.constraintSearch.getLayoutParams() as RelativeLayout.LayoutParams
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        binding.constraintSearch.setLayoutParams(params)
    }
}



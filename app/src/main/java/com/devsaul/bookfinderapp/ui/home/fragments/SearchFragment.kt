package com.devsaul.bookfinderapp.ui.home.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.FragmentSearchBinding
import com.devsaul.bookfinderapp.ui.home.adapters.BooksAdapter
import com.devsaul.bookfinderapp.ui.home.viewmodel.SearchViewModel
import com.devsaul.bookfinderapp.utils.hide
import com.devsaul.bookfinderapp.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()

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
            validateInputs()
        }
        binding.btnFav.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_favFragment)
        }
        viewModel.books.observe(viewLifecycleOwner){
            binding.loadingLayout.hide()
            binding.RecyclerListTickets.adapter = BooksAdapter(it)
        }
    }

    private fun setUpView() {

        binding.RecyclerListTickets.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = BooksAdapter(mutableListOf())
        }

        setSetOnCenter()

    }

    fun validateInputs() {
        if (binding.searchEditText.text.isNullOrEmpty()) {
            binding.searchTextField.error = getString(R.string.MSG_SEARCH_ERRO)
        }else{
            binding.searchTextField.error = null
            setSetOnTop()
            hiddenKeyboard()
            binding.divider.show()
            binding.loadingLayout.show()
            binding.RecyclerListTickets.show()
            viewModel.getBookByTitle(binding.searchEditText.text.toString())
        }
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

    private fun hiddenKeyboard() {
        val imm = requireActivity().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(
            binding.searchEditText.getWindowToken(),
            0
        )
    }
}
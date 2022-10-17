package com.devsaul.bookfinderapp.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.FragmentFavBinding
import com.devsaul.bookfinderapp.databinding.MaterialAlertBookBinding
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.ui.home.adapters.BooksAdapter
import com.devsaul.bookfinderapp.ui.home.viewmodel.FavViewModel
import com.devsaul.bookfinderapp.utils.hide
import com.devsaul.bookfinderapp.utils.setUpViewAler
import com.devsaul.bookfinderapp.utils.show
import com.devsaul.bookfinderapp.utils.toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding
    private val favViewModel by viewModels<FavViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_favFragment_to_searchFragment)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        setUpView()
        manageEvents()
    }

    private fun loadData() {
        favViewModel.getFavorites()
    }

    private fun manageEvents() {
        favViewModel.books.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty()){
                binding.lnErrorInfo.hide()
                binding.RecyclerList.adapter = BooksAdapter(it.toMutableList(), this@FavFragment,favViewModel)
            }else{
                binding.lnErrorInfo.show()
            }
        }
    }

    private fun setUpView() {
        binding.RecyclerList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = BooksAdapter(mutableListOf(), this@FavFragment,favViewModel)
        }
    }


}
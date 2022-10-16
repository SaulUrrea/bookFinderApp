package com.devsaul.bookfinderapp.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.FragmentFavBinding
import com.devsaul.bookfinderapp.databinding.FragmentSearchBinding


class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding

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


}
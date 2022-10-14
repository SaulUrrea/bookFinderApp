package com.devsaul.bookfinderapp.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.FragmentFavBinding
import com.devsaul.bookfinderapp.databinding.FragmentSearchBinding


class FavFragment : Fragment() {

    private lateinit var binding: FragmentFavBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }


}
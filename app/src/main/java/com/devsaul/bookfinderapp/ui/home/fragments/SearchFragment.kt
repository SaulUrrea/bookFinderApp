package com.devsaul.bookfinderapp.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.devsaul.bookfinderapp.R
import com.devsaul.bookfinderapp.databinding.FragmentSearchBinding
import com.devsaul.bookfinderapp.databinding.MaterialAlertBookBinding
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.ui.home.adapters.BooksAdapter
import com.devsaul.bookfinderapp.ui.home.viewmodel.SearchViewModel
import com.devsaul.bookfinderapp.utils.hiddenKeyboard
import com.devsaul.bookfinderapp.utils.hide
import com.devsaul.bookfinderapp.utils.setBookInfo
import com.devsaul.bookfinderapp.utils.show
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
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
            binding.RecyclerListTickets.adapter = BooksAdapter(it, this@SearchFragment)
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
            adapter = BooksAdapter(mutableListOf(), this@SearchFragment)
        }

        setSetOnCenter()

    }

    fun setAlert(book: Book) {
        val bindingAlert: MaterialAlertBookBinding =
            MaterialAlertBookBinding.inflate(layoutInflater)
        val bookDialogAlert = MaterialAlertDialogBuilder(
            requireContext(),
            com.google.android.material.R.style.MaterialAlertDialog_Material3
        )
            .setView(bindingAlert.root)
            .setNegativeButton(getString(R.string.close)) { dialog, which ->
                dialog.dismiss()
            }
            .create()
        bookDialogAlert.show()
        setUpViewAler(book, bindingAlert)
    }

    private fun setUpViewAler(book: Book, bindingAlert: MaterialAlertBookBinding) {

        bindingAlert.tvSubtitle.setText(book.title)

        val authorText = "Autor: " + (book.author_name?.joinToString(", ") ?: "a")
        val dateText = "Fecha publicación: " + book.first_publish_year.toString()
        val pageText = "Numero de paginas: " + book.number_of_pages_median.toString()
        val colaboradoresText = "Colaboradores: " + book.contributor?.joinToString(", ")
        val placesText = "Lugares de publicación: " + book.publish_place?.joinToString(
            ", "
        )
        val editorsText = "Editoriales: " + book.publisher?.joinToString(", ")
        val isbnText = "ISBN: " + book.isbn?.get(0).toString()
        val languageText = "Lenguajes: " + book.language?.joinToString(", ")
        val personsText = "Pensonajes: " + book.person?.joinToString(", ")

        bindingAlert.tvAuthor.setBookInfo(authorText, bindingAlert.tvAuthor)
        bindingAlert.tvDate.setBookInfo(dateText, bindingAlert.tvDate)
        bindingAlert.tvPagNumber.setBookInfo(pageText, bindingAlert.tvPagNumber)
        bindingAlert.tvContributors.setBookInfo(colaboradoresText, bindingAlert.tvContributors)
        bindingAlert.tvPublishPlaces.setBookInfo(placesText, bindingAlert.tvPublishPlaces)
        bindingAlert.tvPublisher.setBookInfo(editorsText, bindingAlert.tvPublisher)
        bindingAlert.tvIsbn.setBookInfo(isbnText, bindingAlert.tvIsbn)
        bindingAlert.tvLanguage.setBookInfo(languageText, bindingAlert.tvLanguage)
        bindingAlert.tvPersons.setBookInfo(personsText, bindingAlert.tvPersons)

        val imgUrl = "https://covers.openlibrary.org/b/isbn/${book.isbn?.get(0)}-M.jpg"
        Picasso.get()
            .load(imgUrl)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.drawable.ic_image_not_supported)
            .into(bindingAlert.imgBook)
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



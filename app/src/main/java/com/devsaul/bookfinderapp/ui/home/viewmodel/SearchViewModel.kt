package com.devsaul.bookfinderapp.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.domain.usecases.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBookUseCase: GetBooksUseCase
) : ViewModel() {
    val books = MutableLiveData<List<Book>>()

    fun getBookByTitle(title: String) {
        viewModelScope.launch {
            books.postValue(searchBookUseCase.searchBook(title =  title))
        }
    }

    fun getBookByAuthor(author: String) {
        viewModelScope.launch {
            books.postValue(searchBookUseCase.searchBook(author= author))
        }
    }


}
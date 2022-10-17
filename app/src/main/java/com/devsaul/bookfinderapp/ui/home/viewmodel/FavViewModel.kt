package com.devsaul.bookfinderapp.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devsaul.bookfinderapp.domain.models.Book
import com.devsaul.bookfinderapp.domain.usecases.SetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(
    private val setFavoritesUseCase: SetFavoritesUseCase
): ViewModel() {
    val books = MutableLiveData<List<Book>?>()
    val bookIsRemove = MutableLiveData<Boolean>()

    fun getFavorites() {
        viewModelScope.launch {
            books.postValue(setFavoritesUseCase.getFavoritesBooks())
        }
    }

    fun addFavorite(book: Book){
        viewModelScope.launch {
            setFavoritesUseCase.addFavoriteBook(book)
        }
    }

    fun removeFavorite(book: Book){
        viewModelScope.launch {
            setFavoritesUseCase.removeFavoriteBook(book)
            bookIsRemove.postValue(true)
        }
    }
}
package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mluzzi.bookshelfapp.model.BookItem
import com.mluzzi.bookshelfapp.network.RetrofitInstance
import kotlinx.coroutines.launch

class BookshelfViewModel : ViewModel() {
    private val _books = MutableLiveData<List<BookItem>>()
    val books: LiveData<List<BookItem>> = _books

    init {
        fetchBooks("flowers")
    }

    private fun fetchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getBooks(query)
                _books.value = response.items
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}
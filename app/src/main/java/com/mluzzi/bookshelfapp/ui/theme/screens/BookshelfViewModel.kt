package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mluzzi.bookshelfapp.BookshelfApplication
import com.mluzzi.bookshelfapp.model.BookshelfRepository
import com.mluzzi.bookshelfapp.network.BookItem
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface BookshelfUiState {
    data class Success(val books: List<BookItem>) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}

class BookshelfViewModel(private val bookshelfRepository: BookshelfRepository) : ViewModel() {

    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            bookshelfUiState = try {
                BookshelfUiState.Success(bookshelfRepository.getBooks("game+thrones"))
            } catch (e: IOException) {
                BookshelfUiState.Error
            } catch (e: HttpException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}

//class BookshelfViewModel : ViewModel() {
//    private val _books = MutableLiveData<List<BookItem>>()
//    val books: LiveData<List<BookItem>> = _books
//
//    init {
//        fetchBooks("flowers")
//    }
//
//    private fun fetchBooks(query: String) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.getBooks(query)
//                _books.value = response.items
//            } catch (e: Exception) {
//                // Handle the error
//            }
//        }
//    }
//}
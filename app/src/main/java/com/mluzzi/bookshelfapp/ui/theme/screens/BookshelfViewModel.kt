package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mluzzi.bookshelfapp.BookshelfApplication
import com.mluzzi.bookshelfapp.model.BookshelfRepository
import com.mluzzi.bookshelfapp.network.BookItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface BookshelfUiState {
    data class Success(val books: List<BookItem>) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState

    data class BookDetailsSuccess(val bookDetails: BookItem) : BookshelfUiState
    object BookDetailsError : BookshelfUiState
    object BookDetailsLoading : BookshelfUiState
}

class BookshelfViewModel(private val bookshelfRepository: BookshelfRepository) : ViewModel() {

    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set


    fun getBooks(query: String) {
        viewModelScope.launch {
            bookshelfUiState = try {
                BookshelfUiState.Success(bookshelfRepository.getBooks(query))
            } catch (e: IOException) {
                BookshelfUiState.Error
            } catch (e: HttpException) {
                BookshelfUiState.Error
            }
        }
    }

    var bookDetailsUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.BookDetailsLoading)
        private set

    fun getBookDetails(bookId: String) {
        viewModelScope.launch {
            bookDetailsUiState = try {
                val bookDetails = bookshelfRepository.getBookDetails(bookId)
                if (bookDetails != null) {
                    BookshelfUiState.BookDetailsSuccess(bookDetails)
                } else {
                    BookshelfUiState.BookDetailsError
                }
            } catch (e: IOException) {
                BookshelfUiState.BookDetailsError
            } catch (e: HttpException) {
                BookshelfUiState.BookDetailsError
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
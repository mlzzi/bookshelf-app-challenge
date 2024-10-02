package com.mluzzi.bookshelfapp.fake

import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfUiState
import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookshelfViewModelTest {

    @Test
    fun bookshelfViewModel_getBooks_verifyBookshelfUiStateSuccess() =
        runTest {
            val bookshelfViewModel = BookshelfViewModel(
                bookshelfRepository = FakeNetworkBookshelfRepository()
            )
            assertEquals(
                BookshelfUiState.Success(FakeDataSource.bookList),
                bookshelfViewModel.bookshelfUiState
            )
        }
}

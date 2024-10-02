package com.mluzzi.bookshelfapp.fake

import com.mluzzi.bookshelfapp.model.NetworkBookshelfRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkRepositoryTest {

    @Test
    fun networkRepository_getBooks_verifyBookList() = runTest {
        val repository = NetworkBookshelfRepository(
            bookshelApiService = FakeBooksApiService()
        )
        assertEquals(FakeDataSource.bookResponse.items, repository.getBooks("testQuery"))
    }

}
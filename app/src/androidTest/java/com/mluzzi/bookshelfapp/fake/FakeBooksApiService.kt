package com.mluzzi.bookshelfapp.fake

import com.mluzzi.bookshelfapp.network.BookResponse
import com.mluzzi.bookshelfapp.network.BooksApiService

class FakeBooksApiService: BooksApiService {
    override suspend fun getBooks(query: String): BookResponse {
        return FakeDataSource.bookResponse
    }
}
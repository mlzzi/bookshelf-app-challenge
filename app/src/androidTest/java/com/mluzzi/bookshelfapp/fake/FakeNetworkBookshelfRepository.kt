package com.mluzzi.bookshelfapp.fake

import com.mluzzi.bookshelfapp.model.BookshelfRepository
import com.mluzzi.bookshelfapp.network.BookItem

class FakeNetworkBookshelfRepository: BookshelfRepository {
    override suspend fun getBooks(query: String): List<BookItem> {
        return FakeDataSource.bookList
    }
}
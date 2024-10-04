package com.mluzzi.bookshelfapp.model

import com.mluzzi.bookshelfapp.network.BookItem
import com.mluzzi.bookshelfapp.network.BooksApiService

interface BookshelfRepository {
    suspend fun getBooks(query: String): List<BookItem>

    suspend fun getBookDetails(id: String): BookItem
}

class NetworkBookshelfRepository(
    private val bookshelApiService: BooksApiService
) : BookshelfRepository {
    override suspend fun getBooks(query: String): List<BookItem> =
        bookshelApiService.getBooks(query).items

    override suspend fun getBookDetails(id: String): BookItem =
        bookshelApiService.getBookDetails(id)
}
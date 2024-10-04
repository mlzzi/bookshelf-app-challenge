package com.mluzzi.bookshelfapp.network

import androidx.compose.ui.graphics.Path
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse

    @GET("volumes/{id}")
    suspend fun getBookDetails(@retrofit2.http.Path("id") id: String): BookItem
}
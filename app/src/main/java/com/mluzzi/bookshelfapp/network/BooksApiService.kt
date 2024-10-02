package com.mluzzi.bookshelfapp.network

import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse
}
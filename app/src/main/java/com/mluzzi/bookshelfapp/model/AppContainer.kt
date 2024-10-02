package com.mluzzi.bookshelfapp.model

import com.mluzzi.bookshelfapp.network.BooksApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookshelfRepository: BookshelfRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://www.googleapis.com/books/v1/"

    private val retofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BooksApiService by lazy {
        retofit.create(BooksApiService::class.java)
    }

    override val bookshelfRepository: BookshelfRepository by lazy {
        NetworkBookshelfRepository(retrofitService)
    }
}
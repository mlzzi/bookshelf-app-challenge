package com.mluzzi.bookshelfapp.model

import com.mluzzi.bookshelfapp.network.BooksApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
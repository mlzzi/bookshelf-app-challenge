package com.mluzzi.bookshelfapp.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("items")
    val items: List<BookItem>
)

data class BookItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    @SerializedName("title")
    val title: String,
    @SerializedName("authors")
    val authors: List<String>?
)

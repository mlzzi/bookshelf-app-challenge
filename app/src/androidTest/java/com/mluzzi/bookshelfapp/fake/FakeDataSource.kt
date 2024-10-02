package com.mluzzi.bookshelfapp.fake

import com.mluzzi.bookshelfapp.network.BookItem
import com.mluzzi.bookshelfapp.network.BookResponse
import com.mluzzi.bookshelfapp.network.ImageLinks
import com.mluzzi.bookshelfapp.network.VolumeInfo

object FakeDataSource {

    const val idOne = "book1"
    const val idTwo = "book2"
    const val titleOne = "title1"
    const val titleTwo = "title2"
    const val author1 = "author1"
    const val author2 = "author2"
    const val author3 = "author3"
    const val thumbnail1 = "thumbnail1"
    const val thumbnail2 = "thumbnail2"


    val bookList = listOf(
        BookItem(
            id = idOne,
            volumeInfo = VolumeInfo(
                title = titleOne,
                authors = listOf(author1),
                imageLinks = ImageLinks(
                    thumbnail = thumbnail1
                )
            ),
        ),
        BookItem(
            id = idTwo,
            volumeInfo = VolumeInfo(
                title = titleTwo,
                authors = listOf(author2, author3),
                imageLinks = ImageLinks(
                    thumbnail = thumbnail2
                )
            ),
        )
    )

    val bookResponse = BookResponse(
        items = listOf(
            BookItem(
                id = idOne,
                volumeInfo = VolumeInfo(
                    title = titleOne,
                    authors = listOf(author1),
                    imageLinks = ImageLinks(
                        thumbnail = thumbnail1
                    )
                ),
            ),
            BookItem(
                id = idTwo,
                volumeInfo = VolumeInfo(
                    title = titleTwo,
                    authors = listOf(author2, author3),
                    imageLinks = ImageLinks(
                        thumbnail = thumbnail2
                    )
                ),
            )
        )
    )
}
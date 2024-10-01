package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mluzzi.bookshelfapp.model.BookItem

@Composable
fun HomeScreen(viewModel: BookshelfViewModel = viewModel()) {
    val books by viewModel.books.observeAsState(emptyList())

    LazyColumn {
        items(books) { book ->
            BookItemView(book)
        }
    }
}

@Composable
fun BookItemView(book: BookItem) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "ID: ${book.id}")
        Text(text = "Title: ${book.volumeInfo.title}")
        Text(
            text = "Authors: ${book.volumeInfo.authors?.joinToString(", ")}"
        )
    }
}
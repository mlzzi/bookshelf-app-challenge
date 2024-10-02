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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mluzzi.bookshelfapp.R
import com.mluzzi.bookshelfapp.network.BookItem

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
        Text(text = "Title: ${book.volumeInfo.title}")
        Text(
            text = "Authors: ${book.volumeInfo.authors?.joinToString(", ")}"
        )
        val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.padding(top = 8.dp),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground)
        )
    }
}
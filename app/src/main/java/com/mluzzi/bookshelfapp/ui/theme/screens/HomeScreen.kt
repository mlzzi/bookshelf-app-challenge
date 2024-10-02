package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mluzzi.bookshelfapp.R
import com.mluzzi.bookshelfapp.network.BookItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    bookshelfUiState: BookshelfUiState,
    retryAction: () -> Unit
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen()
        is BookshelfUiState.Success -> BookshelfList(books = bookshelfUiState.books)
        is BookshelfUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, retryAction: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = ""
        )
        Text(text = stringResource(id = R.string.loading_failed), Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(id = R.string.loading)
    )
}

@Composable
fun BookshelfList(books: List<BookItem>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = books, key = { book -> book.id }) { book ->
            BookItemView(book = book)
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
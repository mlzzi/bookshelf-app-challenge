package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mluzzi.bookshelfapp.R
import com.mluzzi.bookshelfapp.network.BookItem

@Composable
fun BookInfoScreen(
    bookId: String,
    bookshelfViewModel: BookshelfViewModel,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {
    val bookDetailsUiState = bookshelfViewModel.bookDetailsUiState

    LaunchedEffect(bookId) {
        bookshelfViewModel.getBookDetails(bookId)

    }

    when (bookDetailsUiState) {
        is BookshelfUiState.BookDetailsLoading -> LoadingScreen()
        is BookshelfUiState.BookDetailsSuccess -> BookCard(bookDetailsUiState.bookDetails)
        is BookshelfUiState.BookDetailsError -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )

        else -> {}
    }
}

@Composable
fun BookCard(bookItem: BookItem) {
    LazyColumn {
        item {
            bookDetails(bookItem)
        }
    }
}

@Composable
fun bookDetails(
    bookItem: BookItem
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val imageUrl = bookItem.volumeInfo.imageLinks?.small?.replace("http", "https")
        val description = bookItem.volumeInfo.description?.replace(Regex("<[^>]*>"), "")
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            placeholder = painterResource(R.drawable.loading_image),
            error = painterResource(R.drawable.broken_image)
        )
        Text(
            text = bookItem.volumeInfo.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            ),
            modifier = Modifier.padding(8.dp)
        )
        if (bookItem.volumeInfo.subtitle != null) {
            Text(
                text = bookItem.run { volumeInfo.subtitle.toString() },
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Text(
            text = "${bookItem.volumeInfo.authors?.joinToString(", ")}",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = description.toString(),
            style = TextStyle(fontSize = 20.sp),
            )
    }
}

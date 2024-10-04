package com.mluzzi.bookshelfapp.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mluzzi.bookshelfapp.R
import com.mluzzi.bookshelfapp.network.BookItem

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    bookshelfUiState: BookshelfUiState,
    navController: NavHostController,
    retryAction: () -> Unit
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen()
        is BookshelfUiState.Success -> BookshelfList(books = bookshelfUiState.books, navController = navController)
        is BookshelfUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )

        else -> {}
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
            painter = painterResource(id = R.drawable.broken_image),
            contentDescription = "",
            modifier = Modifier.size(100.dp)
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
        painter = painterResource(id = R.drawable.loading_image),
        contentDescription = stringResource(id = R.string.loading)
    )
}

@Composable
fun BookshelfList(
    books: List<BookItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(4.dp)
    ) {
        items(items = books, key = { book -> book.id }) { book ->
            BookItem(book = book) { bookId ->
                navController.navigate("bookInfo/$bookId")
            }
        }
    }
}

@Composable
fun BookItem(book: BookItem, onBookClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable { onBookClick(book.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(180.dp),
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.loading_image),
                error = painterResource(R.drawable.broken_image),
            )
        }
    }
}

//@Preview
//@Composable
//fun LoadingScreenPreview() {
//    BookshelfList(books = listOf())
//}
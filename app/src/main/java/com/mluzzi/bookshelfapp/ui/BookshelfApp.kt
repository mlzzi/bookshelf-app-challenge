@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.mluzzi.bookshelfapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mluzzi.bookshelfapp.R
import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfViewModel
import com.mluzzi.bookshelfapp.ui.theme.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            BookshelfTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val bookshelfViewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)
            HomeScreen(
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                retryAction = bookshelfViewModel::getBooks
            )
        }
    }
    val viewModel: BookshelfViewModel = viewModel(
        factory = BookshelfViewModel.Factory
    )
}

@Composable
fun BookshelfTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(title = {
        Text(text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
        )
    })
}

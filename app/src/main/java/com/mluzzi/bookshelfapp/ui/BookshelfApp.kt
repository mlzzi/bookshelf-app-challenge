@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mluzzi.bookshelfapp.R
import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfUiState
import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfViewModel
import com.mluzzi.bookshelfapp.ui.theme.screens.HomeScreen
import com.mluzzi.bookshelfapp.ui.theme.screens.ResultScreen

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
            Navigation(
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                bookshelfViewModel = bookshelfViewModel
                )
        }
    }
}

@Composable
fun BookshelfTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(title = {
        Text(text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
        )
    })
}

@Composable
fun Navigation(bookshelfUiState: BookshelfUiState, bookshelfViewModel: BookshelfViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController, bookshelfViewModel) }
        composable(
            "result/{searchText}",
            arguments = listOf(navArgument("searchText") { type = NavType.StringType })
        ) { backStackEntry ->
            ResultScreen(
                bookshelfUiState = bookshelfUiState,
                retryAction = { navController.navigate("home") }
            )
        }
    }
}

package com.mluzzi.bookshelfapp

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfUiState
import com.mluzzi.bookshelfapp.ui.theme.screens.BookshelfViewModel
import com.mluzzi.bookshelfapp.ui.theme.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
//class UiTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testSearch() {
//        composeTestRule.setContent {
//            HomeScreen(
//                navController = NavHostController(LocalContext.current),
//                bookshelfViewModel = FakeBookshelfViewModel()
//            )
//        }
//
//        composeTestRule.onNodeWithTag("searchTextField") // Replace with your actual test tag
//            .performTextInput("game of thrones")
//
//        composeTestRule.onNodeWithTag("searchButton") // Replace with your actual test tag
//            .performClick()
//
//        // Assert that the expected behavior occurred (e.g., a new screen is displayed)
//        composeTestRule.onNodeWithText("Results").assertIsDisplayed()
//    }
//
//}
//
//class FakeBookshelfViewModel() : BookshelfViewModel() {
//    // Override methods to provide fake data or behavior
//    override fun searchBooks(query: String) {
//        // Update the UI state with fake data
//        _bookshelfUiState.value = BookshelfUiState.Success(
//            listOf(
//                // ... your fake book items
//            )
//        )
//    }
//}
package com.mluzzi.bookshelfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mluzzi.bookshelfapp.ui.BookshelfApp
import com.mluzzi.bookshelfapp.ui.theme.BookshelfAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookshelfAppTheme {
                BookshelfApp()
            }
        }
    }
}
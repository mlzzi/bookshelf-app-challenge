package com.mluzzi.bookshelfapp

import android.app.Application
import com.mluzzi.bookshelfapp.model.AppContainer
import com.mluzzi.bookshelfapp.model.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
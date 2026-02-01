package com.example.cmp_sqlite_demo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.cmp_sqlite_demo.data.AppDatabase
import com.example.cmp_sqlite_demo.ui.ListingScreen

@Composable
fun App(database: AppDatabase) {
    MaterialTheme {
        ListingScreen(database)
    }
}
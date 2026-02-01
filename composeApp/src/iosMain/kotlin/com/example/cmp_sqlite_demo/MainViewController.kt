package com.example.cmp_sqlite_demo

import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmp_sqlite_demo.data.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    App(getDatabaseBuilder().build())
}
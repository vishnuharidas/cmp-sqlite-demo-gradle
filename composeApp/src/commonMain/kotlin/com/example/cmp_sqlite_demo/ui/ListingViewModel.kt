package com.example.cmp_sqlite_demo.ui

import androidx.lifecycle.ViewModel
import com.example.cmp_sqlite_demo.data.AppDatabase
import com.example.cmp_sqlite_demo.data.LogEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListingViewModel(val database: AppDatabase) : ViewModel() {

    private val _items = MutableStateFlow<List<LogEntry>>(emptyList())
    val items: StateFlow<List<LogEntry>> = _items.asStateFlow()

    fun addItem(text: String) {
        if (text.isBlank()) return
        val newItem = LogEntry(
            id = kotlin.time.Clock.System.now().toEpochMilliseconds(),
            text = text
        )
        val currentList = _items.value.toMutableList()
        currentList.add(newItem)
        _items.value = currentList
    }
}

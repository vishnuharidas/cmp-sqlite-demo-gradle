package com.example.cmp_sqlite_demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cmp_sqlite_demo.data.AppDatabase
import com.example.cmp_sqlite_demo.data.LogEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListingViewModel(val database: AppDatabase) : ViewModel() {

    val dbItems = database.logEntryDao().getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    fun addItem(text: String) {
        if (text.isBlank()) return
        val newItem = LogEntry(
            id = kotlin.time.Clock.System.now().toEpochMilliseconds(),
            text = text
        )

        viewModelScope.launch {
            database.logEntryDao().insert(newItem)
        }

    }
}

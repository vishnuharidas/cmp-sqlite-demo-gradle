package com.example.cmp_sqlite_demo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LogEntry(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val text: String
)

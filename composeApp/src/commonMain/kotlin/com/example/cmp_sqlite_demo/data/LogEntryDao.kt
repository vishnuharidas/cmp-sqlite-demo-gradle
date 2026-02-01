package com.example.cmp_sqlite_demo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {
    @Insert
    suspend fun insert(item: LogEntry)

    @Query("SELECT * FROM LogEntry ORDER BY id DESC")
    fun getAll(): Flow<List<LogEntry>>
}

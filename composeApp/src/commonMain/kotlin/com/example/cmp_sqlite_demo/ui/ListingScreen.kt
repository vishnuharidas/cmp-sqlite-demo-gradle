package com.example.cmp_sqlite_demo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cmp_sqlite_demo.data.AppDatabase
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingScreen(database: AppDatabase) {
    val viewModel = viewModel { ListingViewModel(database) }
    val items by viewModel.dbItems.collectAsState()
    var text by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Items List") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(items) { item ->
                    val time = Instant.fromEpochMilliseconds(item.id).toLocalDateTime(TimeZone.currentSystemDefault())
                    ListItem(
                        headlineContent = { Text(item.text) },
                        overlineContent = { Text("Time: ${time.date} ${time.time}") },
                        trailingContent = {
                            IconButton(onClick = { viewModel.deleteItem(item.id) }) {
                                Icon(Icons.Outlined.Delete, "Delete Item")
                            }
                        }
                    )
                    HorizontalDivider()
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.weight(1f),
                    label = { Text("New Item") }
                )
                Button(
                    onClick = {
                        viewModel.addItem(text)
                        text = ""
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Add")
                }
            }
        }
    }
}

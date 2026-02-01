package com.example.cmp_sqlite_demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.example.webtonative.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "url_history")
data class UrlEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val timestamp: Long
)

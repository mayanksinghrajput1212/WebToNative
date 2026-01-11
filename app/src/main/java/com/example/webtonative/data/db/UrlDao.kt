package com.example.webtonative.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UrlDao {

    @Insert
    suspend fun insert(entity: UrlEntity)

    @Query("SELECT * FROM url_history ORDER BY timestamp DESC")
    suspend fun getAll(): List<UrlEntity>

    @Query("DELETE FROM url_history")
    suspend fun deleteAll()
}

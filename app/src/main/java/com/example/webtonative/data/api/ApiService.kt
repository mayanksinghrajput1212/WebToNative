package com.example.webtonative.data.api

import com.example.webtonative.data.db.UrlEntity
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("urls")
    suspend fun uploadUrls(
        @Body list: List<UrlEntity>
    )
}

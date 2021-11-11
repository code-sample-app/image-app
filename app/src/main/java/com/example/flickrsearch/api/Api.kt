package com.example.flickrsearch.api

import com.example.flickrsearch.data.SearchPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=5a2cc90782760b3a6b3eca570dfaf5c3")
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): SearchPhotoResponse
}
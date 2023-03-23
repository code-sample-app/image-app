package com.example.flickrsearch.model


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrsearch.api.Client
import com.example.flickrsearch.data.Photo

class PhotoViewModel : ViewModel() {
    var photos = MutableLiveData<MutableList<Photo>>()

    fun sortImages(photoList: List<Photo>) {
        photos.value?.clear()
       photos.postValue(photoList as MutableList<Photo>?)
    }

    suspend fun fetchImages(searchTerm: String): List<Photo> {
        if (searchTerm.isBlank()) {
            return emptyList()
        }

        val searchResponse = Client.client.fetchImages(searchTerm)
        Log.e("!!!", searchResponse.toString())
        return searchResponse.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "http://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }
    }

    }

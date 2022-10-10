package com.example.photogallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class PhotoGalleryViewModel(private val app: Application) : AndroidViewModel(app) {
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>(false)
    val searchTerm: String
        get() = mutableSearchTerm.value ?: ""

    init {
        isLoading.value = true
        mutableSearchTerm.value = QueryPreferences.getStoredQuery(app)
        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm) {
            if (it.isBlank()) {
                flickrFetchr.fetchPhotos()
            } else flickrFetchr.searchPhoto(it)

        }
        isLoading.value = false
    }

    fun fetchPhoto(query: String = "") {
        QueryPreferences.setStoredQuery(app, query)
        mutableSearchTerm.value = query
    }
}
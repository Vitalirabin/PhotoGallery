package com.example.photogallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PhotoGalleryViewModel : ViewModel() {
    val galleryItemLiveData: LiveData<List<GalleryItem>>
    private val flickrFetchr = FlickrFetchr()
    private val mutableSearchTerm = MutableLiveData<String>()

    init {
        mutableSearchTerm.value="planets"
        galleryItemLiveData = Transformations.switchMap(mutableSearchTerm){
            flickrFetchr.searchPhoto(it)
        }
    }

    fun fetchPhoto(query:String=""){
        mutableSearchTerm.value=query
    }
}
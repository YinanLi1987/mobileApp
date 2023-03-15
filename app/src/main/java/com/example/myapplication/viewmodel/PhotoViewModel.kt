package com.example.myapplication.viewmodel

import android.provider.ContactsContract.Contacts.Photo
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Pho
import com.example.myapplication.data.PhotosApi
import kotlinx.coroutines.launch

class PhotoViewModel : ViewModel() {
    var photos = mutableStateListOf<Pho>()
        private set

    init {
        getPhotosList()
    }

    private fun getPhotosList() {
        viewModelScope.launch {
            var photosApi: PhotosApi? = null
            try {
                photosApi = PhotosApi!!.getInstance()
                photos.clear()
                photos.addAll(photosApi.getPhotos002())
            } catch (e: Exception) {
                Log.d("PHOTOVIEWMODE", e.message.toString())
            }
        }
    }


}
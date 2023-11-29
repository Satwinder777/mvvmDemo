package com.example.mapiexample.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mapiexample.model.PhotosResponceItem
import com.example.mapiexample.retrofit.ApiInterface

class PhotosRepository(private var api :ApiInterface) {

    private var photosList = MutableLiveData<ArrayList<PhotosResponceItem>>()
    var pList : LiveData<ArrayList<PhotosResponceItem>> = photosList
    suspend fun getPhotos():Boolean{
        return if(api.getPhotos().code()==200){
            photosList.postValue(api.getPhotos().body())
            true
        }else{
            Log.e("error", "getPhotos:${api.getPhotos().code()} >>${api.getPhotos().message()}  ", )
            false
        }
    }
}
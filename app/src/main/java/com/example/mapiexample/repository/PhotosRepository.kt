package com.example.mapiexample.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mapiexample.model.PhotosResponceItem
import com.example.mapiexample.retrofit.ApiInterface
import com.example.mapiexample.utils.Utils.client_id

class PhotosRepository(private var api :ApiInterface) {

    private var photosList = MutableLiveData<ArrayList<PhotosResponceItem>>()
    var pList : LiveData<ArrayList<PhotosResponceItem>> = photosList
    suspend fun getPhotos(id:String):Boolean{
        return if(api.getPhotos(id).code()==200){
            photosList.postValue(api.getPhotos(id).body())
            Log.e("Success111", "getPhotos:${api.getPhotos(id).code()} >> ${api.getPhotos(id).message()}  ", )

            true
        }else{
            Log.e("error", "getPhotos:${api.getPhotos(client_id).code()} >> ${api.getPhotos(client_id).message()}  ", )
            false
        }
    }
}
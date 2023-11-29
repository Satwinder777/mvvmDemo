package com.example.mapiexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapiexample.model.PhotosResponceItem
import com.example.mapiexample.repository.PhotosRepository
import com.example.mapiexample.utils.Utils.client_id
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotosViewModel(var repository: PhotosRepository):ViewModel() {

    private var ListPhotos = MutableLiveData<ArrayList<PhotosResponceItem>>()
    var photosList : LiveData<ArrayList<PhotosResponceItem>> = ListPhotos

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var isSucces = repository.getPhotos(client_id)
            if (isSucces==true){
                ListPhotos.postValue(repository.pList.value)
            }else{
                Log.e("errorSI", "$isSucces " )
            }
        }
    }


}

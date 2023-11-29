package com.example.mapiexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mapiexample.repository.PhotosRepository

class PhotosViewModelFactory(var repository: PhotosRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosViewModel(repository) as T
    }
}
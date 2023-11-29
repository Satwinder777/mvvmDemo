package com.example.mapiexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mapiexample.R
import com.example.mapiexample.repository.PhotosRepository
import com.example.mapiexample.retrofit.RetrofitHelper
import com.example.mapiexample.viewmodel.PhotosViewModel
import com.example.mapiexample.viewmodel.PhotosViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: PhotosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var repo = PhotosRepository(RetrofitHelper.service)
        viewModel = ViewModelProvider(this,PhotosViewModelFactory(repo)).get(PhotosViewModel::class.java)

        viewModel.photosList.observe(this, Observer {
            it.forEach {
                Log.e("PhotosId", "onCreate: ${it.id}")
            }
        })
    }
}
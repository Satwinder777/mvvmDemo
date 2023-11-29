package com.example.mapiexample.retrofit

import com.example.mapiexample.model.PhotosResponce
import com.example.mapiexample.model.PhotosResponceItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/photos/")
    suspend fun getPhotos(@Query("client_id") page: String):Response<ArrayList<PhotosResponceItem>>
}
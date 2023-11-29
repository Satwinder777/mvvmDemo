package com.example.mapiexample.retrofit

import com.example.mapiexample.model.PhotosResponce
import com.example.mapiexample.model.PhotosResponceItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/photos/?client_id=3egSspFPj3mhk38vnliRK41-amsjUoZLdaCZAV2a1zA")
    suspend fun getPhotos():Response<ArrayList<PhotosResponceItem>>
}
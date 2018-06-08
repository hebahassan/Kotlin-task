package com.example.heba.kotlintask.retrofit

import com.example.heba.kotlintask.model.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by heba on 03-Jun-18.
 */

interface ApiInterface {
    @GET("photos")
    fun getPhotos(): Call<List<PhotoResponse>>
}
package com.example.heba.kotlintask.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by heba on 03-Jun-18.
 */

class ApiClient {

    companion object {
        private val BASE_URL: String = "https://jsonplaceholder.typicode.com/"
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit?{
            val gson: Gson = GsonBuilder().setLenient().create()
            retrofit = if (retrofit == null) Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build() else retrofit

            return retrofit
        }
    }
}
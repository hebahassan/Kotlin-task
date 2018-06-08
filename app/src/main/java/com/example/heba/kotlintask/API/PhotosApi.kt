package com.example.heba.kotlintask.API

import android.content.Context
import android.widget.Toast
import com.example.heba.kotlintask.Presenter.PhotoPresenter
import com.example.heba.kotlintask.model.PhotoResponse
import com.example.heba.kotlintask.retrofit.ApiClient
import com.example.heba.kotlintask.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by heba on 03-Jun-18.
 */

class PhotosApi(var context: Context, var photoPresenter: PhotoPresenter){
    private val apiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)
    private val dividedList = mutableListOf<PhotoResponse>()

    /*fun getPhotos(){
        apiInterface.getPhotos().enqueue(object : Callback<List<PhotoResponse>> {
            override fun onResponse(call: Call<List<PhotoResponse>>?, response: Response<List<PhotoResponse>>?) {
                photoPresenter.setPhotosList(response!!.body()!!)
            }

            override fun onFailure(call: Call<List<PhotoResponse>>?, t: Throwable?) {
                Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    fun getPhotosSync(beginningIndex: Int, lastItem: Int) {
        apiInterface.getPhotos().enqueue(object : Callback<List<PhotoResponse>> {
            override fun onFailure(call: Call<List<PhotoResponse>>?, t: Throwable?) {
                Toast.makeText(context, "Please check your Internet connection and try again.", Toast.LENGTH_SHORT).show()
                photoPresenter.setProgressBarGone()
            }

            override fun onResponse(call: Call<List<PhotoResponse>>?, response: Response<List<PhotoResponse>>?) {
                if(response!!.isSuccessful) {
                    if (beginningIndex < response.body()!!.lastIndex){
                        for (counter in beginningIndex..lastItem){
                            dividedList.add(response.body()!![counter])
                        }
                        photoPresenter.setPhotosList(dividedList)
                    }
                    else {
                        photoPresenter.setProgressBarGone()
                        Toast.makeText(context, "End of results..", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    Toast.makeText(context, "Server Error.", Toast.LENGTH_SHORT).show()
                    photoPresenter.setProgressBarGone()
                }
            }

        })
    }
}
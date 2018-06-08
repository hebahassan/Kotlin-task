package com.example.heba.kotlintask.Presenter

import com.example.heba.kotlintask.model.PhotoResponse

/**
 * Created by heba on 03-Jun-18.
 */

interface PhotoPresenter {
    fun setPhotosList(photosList: List<PhotoResponse>)
    fun setProgressBarGone()
}
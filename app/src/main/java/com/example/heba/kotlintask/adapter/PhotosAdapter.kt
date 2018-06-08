package com.example.heba.kotlintask.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.heba.kotlintask.R
import com.example.heba.kotlintask.model.PhotoResponse
import kotlinx.android.synthetic.main.photo_row.view.*

/**
 * Created by heba on 07-Jun-18.
 */

class PhotosAdapter(private val context: Context, private val photosList: List<PhotoResponse>):
        RecyclerView.Adapter<PhotosAdapter.PhotoItemView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemView {
        val v = LayoutInflater.from(context).inflate(R.layout.photo_row, parent, false)
        return PhotoItemView(v)
    }

    override fun onBindViewHolder(holder: PhotoItemView, position: Int) {
        val photo = photosList.get(position)
        holder.bindPhoto(photo, context)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    class PhotoItemView(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bindPhoto(photo: PhotoResponse, context: Context) {
            itemView.TV_title.text = photo.title
            Glide.with(context).load(photo.url).into(itemView.IV_photo)
        }
    }
}
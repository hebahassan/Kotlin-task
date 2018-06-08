package com.example.heba.kotlintask.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.example.heba.kotlintask.API.PhotosApi
import com.example.heba.kotlintask.Presenter.PhotoPresenter
import com.example.heba.kotlintask.R
import com.example.heba.kotlintask.adapter.PhotosAdapter
import com.example.heba.kotlintask.model.PhotoResponse
import kotlinx.android.synthetic.main.activity_photos.*

class PhotosActivity : AppCompatActivity(), PhotoPresenter {
    private lateinit var photoPresenter: PhotoPresenter
    private lateinit var photosApi: PhotosApi
    private lateinit var photoAdapter: PhotosAdapter
    private lateinit var gridLayoutManager: GridLayoutManager

    private var beginningIndex = 0
    private val offset = 20
    private var lastItem = 0
    private var displayedPos = 0

    override fun setPhotosList(photosList: List<PhotoResponse>) {
        progressBar.visibility = View.GONE
        PB_scroll.visibility = View.GONE

        if(!this::photoAdapter.isInitialized){
            photoAdapter = PhotosAdapter(this, photosList)
        }

        RV_photos.adapter = photoAdapter
        gridLayoutManager.scrollToPosition(displayedPos)
    }

    override fun setProgressBarGone() {
        progressBar.visibility = View.GONE
        PB_scroll.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        photoPresenter = this
        photosApi = PhotosApi(this, photoPresenter)

        setProgressBarVisible(progressBar)

        gridLayoutManager = GridLayoutManager(this, 2)
        RV_photos.layoutManager = gridLayoutManager

        setPhotosList()
        onScroll()
    }

    private fun setProgressBarVisible(selectedBar: ProgressBar) {
        selectedBar.visibility = View.VISIBLE
        selectedBar.isIndeterminate = true
    }

    private fun setPhotosList() {
        lastItem = beginningIndex + offset - 1
        photosApi.getPhotosSync(beginningIndex, lastItem)
        beginningIndex = lastItem + 1
    }

    private fun onScroll(){
        RV_photos.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(!recyclerView!!.canScrollVertically(RecyclerView.FOCUS_DOWN)){
                    setProgressBarVisible(PB_scroll)
                    setPhotosList()
                    displayedPos = gridLayoutManager.findFirstVisibleItemPosition() + 2
                }
            }
        })
    }
}

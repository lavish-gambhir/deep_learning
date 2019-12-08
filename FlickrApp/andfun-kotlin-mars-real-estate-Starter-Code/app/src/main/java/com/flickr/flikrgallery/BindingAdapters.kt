package com.flickr.flikrgallery

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.flickr.flikrgallery.overview.FlickrApiStatus
import com.flickr.flikrgallery.overview.PhotoGridAdapter
import com.flickr.flickrgallery.R
import com.flickr.flikrgallery.network.FlickrPhoto


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<FlickrPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}

@BindingAdapter("flickrApiStatus")
fun bindStatus(statusImageView: ImageView, status: FlickrApiStatus?) {
    when (status) {
        FlickrApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FlickrApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FlickrApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
package com.flickr.flikrgallery.overview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flickr.flickrgallery.databinding.GridViewItemBinding
import com.flickr.flikrgallery.network.FlickrPhoto

class PhotoGridAdapter: ListAdapter<FlickrPhoto, PhotoGridAdapter.FlickrPhotoViewHolder>(DiffCallback) {

    class FlickrPhotoViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(flickrPhoto: FlickrPhoto) {
            binding.flickrPhoto = flickrPhoto
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<FlickrPhoto>() {
        override fun areItemsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FlickrPhoto, newItem: FlickrPhoto): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPhotoViewHolder {
        return FlickrPhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FlickrPhotoViewHolder, position: Int) {
        val flickrPhoto = getItem(position)
        holder.bind(flickrPhoto)
    }

}

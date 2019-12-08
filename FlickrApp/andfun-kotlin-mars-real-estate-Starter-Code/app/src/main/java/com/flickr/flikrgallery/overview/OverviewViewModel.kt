package com.flickr.flikrgallery.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flickr.flikrgallery.network.FlickrApi
import com.flickr.flikrgallery.network.FlickrPhoto
import kotlinx.coroutines.*
import java.util.ArrayList


// Status of network calls
enum class FlickrApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel() : ViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
        private var page = 1
    }

    private var _status = MutableLiveData<FlickrApiStatus>()
    val status: LiveData<FlickrApiStatus>
        get() = _status

    private var _imageList = MutableLiveData<ArrayList<FlickrPhoto>>()
    val imageList: LiveData<ArrayList<FlickrPhoto>>
        get() = _imageList

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getDataFromFlickr(1)
    }

    private fun getDataFromFlickr(pageNumber: Int) {
        uiScope.launch {
            val getFlickrDataDeferred = FlickrApi.RETROFIT_SERVICE
                    .getPhotos("flickr.photos.search", "3e7cc266ae2b0e0d78e279ce8e361736", "brazzers", "10", pageNumber, "json", "1")
            try {
                _status.value = FlickrApiStatus.LOADING
                val res = getFlickrDataDeferred.await()
                _status.value = FlickrApiStatus.DONE
                if (_imageList.value == null) {
                    _imageList.value = res.photos.photo
                } else {
                    _imageList.value!!.addAll(res.photos.photo)
                }

            } catch (t: Throwable) {
                _status.value = FlickrApiStatus.ERROR
                _imageList.value = ArrayList() // to clear up the recycler view
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (lastVisibleItemPosition + visibleItemCount + VISIBLE_THRESHOLD >= totalItemCount) {
            getDataFromFlickr(page++)
        }
    }
}

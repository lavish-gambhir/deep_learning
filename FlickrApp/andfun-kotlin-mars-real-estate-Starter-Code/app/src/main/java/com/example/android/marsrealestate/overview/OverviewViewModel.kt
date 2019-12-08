/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


// Status of network calls
enum class FlickrApiStatus { LOADING, ERROR, DONE }

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private var _status = MutableLiveData<FlickrApiStatus>()
    val status: LiveData<FlickrApiStatus>
        get() = _status

    private var _imageList = MutableLiveData<List<FlickrPhoto>>()
    val imageList: LiveData<List<FlickrPhoto>>
        get() = _imageList

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {
            val getFlickrDataDeferred = MarsApi.retrofitService
                    .getPhotos("flickr.photos.search", "3e7cc266ae2b0e0d78e279ce8e361736", "kitten", "10", "json", "1")
            try {
                _status.value = FlickrApiStatus.LOADING
                val res = getFlickrDataDeferred.await()
                _status.value = FlickrApiStatus.DONE
                _imageList.value = res.photos.photo

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
}

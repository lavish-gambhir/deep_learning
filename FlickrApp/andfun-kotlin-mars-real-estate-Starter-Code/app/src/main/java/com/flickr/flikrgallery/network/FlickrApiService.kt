package com.flickr.flikrgallery.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val FLICKR_BASE_URL = "https://api.flickr.com/services/"
private val gson = GsonBuilder()
        .setLenient().create()

//val logger = HttpLoggingInterceptor()
//logger.level = Level.BASIC
//
//val client = OkHttpClient.Builder()
//        .addInterceptor(logger)
//        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(FLICKR_BASE_URL)
        .build()

interface FlickrApiService {
    @GET("rest/")
    fun getPhotos(
            @Query("method")
            method: String,
            @Query("api_key")
            apiKey: String,
            @Query("tags")
            tags: String,
            @Query("per_page")
            perPage: String,
            @Query("page")
            page: Int,
            @Query("format")
            format: String,
            @Query("nojsoncallback")
            nocallback: String
    ): Deferred<FlickrResult>
}


object FlickrApi {
    val RETROFIT_SERVICE: FlickrApiService by lazy {
        retrofit.create(FlickrApiService::class.java)
    }
}
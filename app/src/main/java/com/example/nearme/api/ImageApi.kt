package com.example.nearme.api

import android.location.Location
import com.example.nearme.model.images.ImageModel
import com.example.nearme.model.images.Photo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("/services/rest/?method=flickr.photos.search&api_key=9e5f12168b375780422f42def7e7c339&extras=url_m%2C+geo&per_page=10&format=json&nojsoncallback=1")

   suspend fun getImage(
        @Query("lat") lat:Double,
        @Query("lon")lon:Double):Response<ImageModel>


    suspend fun fetchSinglePicture(): Response<Photo>
    suspend fun updatemap():Response<Location>

}
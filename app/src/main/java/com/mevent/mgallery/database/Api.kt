package com.mevent.mgallery.database

import com.mevent.mgallery.models.ResponseMode
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/api/photos")
    fun getApiResponse(): Call<ResponseMode>
}
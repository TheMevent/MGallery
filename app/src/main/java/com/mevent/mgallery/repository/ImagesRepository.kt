package com.mevent.mgallery.repository

import com.mevent.mgallery.database.ServiceGenerator
import com.mevent.mgallery.models.ResponseMode
import retrofit2.Call

class ImagesRepository {

    private val mApiClient = ServiceGenerator.api

    fun getApiResponse(): Call<ResponseMode>{
        return mApiClient.getApiResponse()
    }

    companion object
    {
        @Volatile
        private var instance:ImagesRepository? = null

        fun getInstance() = instance?: synchronized(this){
            instance?:ImagesRepository().also { instance = it }
        }
    }
}
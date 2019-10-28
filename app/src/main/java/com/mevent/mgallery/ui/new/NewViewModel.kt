package com.mevent.mgallery.ui.new

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mevent.mgallery.models.Data
import com.mevent.mgallery.models.ResponseMode
import com.mevent.mgallery.repository.ImagesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewViewModel : ViewModel() {

    private val imagesRepository = ImagesRepository.getInstance()

    private val getAllImages: MutableLiveData<List<Data>> = MutableLiveData()

    fun getAllImages(): LiveData<List<Data>>
    {
        return getAllImages
    }

    fun getImagesFromNetwork() = imagesRepository.getApiResponse().enqueue(object:
        Callback<ResponseMode> {
        override fun onFailure(call: Call<ResponseMode>, t: Throwable) {
            Log.i("Images" , t.localizedMessage)
        }

        override fun onResponse(
            call: Call<ResponseMode>, response: Response<ResponseMode>
        ) {
            if (response.isSuccessful)
            {
                getAllImages.postValue(response.body()?.data?.filter { it.new })
            }
            else
            {
                getAllImages.postValue(null)
            }
        }
    })
}
/*class NewViewModel : ViewModel() {

    private val imagesRepository = ImagesRepository.getInstance()

    private val getAllImages: MutableLiveData<List<Data>> = MutableLiveData()

    fun getAllImages(): LiveData<List<Data>>
    {
        return getAllImages
    }

    fun getImagesFromNetwork() = imagesRepository.getApiResponse().enqueue(object:
        Callback<ResponseMode> {
        override fun onFailure(call: Call<ResponseMode>, t: Throwable) {
            Log.i("Images" , t.localizedMessage)
        }

        override fun onResponse(
            call: Call<ResponseMode>, response: Response<ResponseMode>
        ) {
            if (response.isSuccessful)
            {
                getAllImages.postValue(response.body()?.data?.filter { it.new })
            }
            else
            {
                getAllImages.postValue(null)
            }
        }
    })
}
*/
/*
: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}*/
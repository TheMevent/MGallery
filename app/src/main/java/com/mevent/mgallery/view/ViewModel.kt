package com.mevent.mgallery.view

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

class ViewModel : ViewModel() {

    private val imagesRepository = ImagesRepository.getInstance()

    private val getAllImages: MutableLiveData<List<Data>> = MutableLiveData()

    fun getAllImages(): LiveData<List<Data>>
    {
        return getAllImages
    }

    fun getImagesFromNetwork(tag: String) = imagesRepository.getApiResponse().enqueue(object:
        Callback<ResponseMode> {
        override fun onFailure(call: Call<ResponseMode>, t: Throwable) {
            Log.i("Images" , t.localizedMessage)
        }

        override fun onResponse(
            call: Call<ResponseMode>, response: Response<ResponseMode>
        ) {
            if (response.isSuccessful)
            {
                getAllImages.postValue(response.body()?.data?.filter { if (tag == "new") it.new else it.popular })
            }
            else
            {
                getAllImages.postValue(null)
            }
        }
    })
}
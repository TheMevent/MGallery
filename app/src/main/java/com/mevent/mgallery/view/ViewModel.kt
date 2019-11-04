package com.mevent.mgallery.view

import android.util.Log
import android.view.View
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mevent.mgallery.models.Data
import com.mevent.mgallery.models.ResponseMode
import com.mevent.mgallery.repository.ImagesRepository
import kotlinx.android.synthetic.main.recycler_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {
/*
    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError
*/
    private val imagesRepository = ImagesRepository.getInstance()

    private val getAllImages: MutableLiveData<List<Data>> = MutableLiveData()

    fun getAllImages(): LiveData<List<Data>>
    {
        return getAllImages
    }

    fun getImagesFromNetwork(tag: String, fragment: Fragment) = imagesRepository.getApiResponse().enqueue(object:
        Callback<ResponseMode> {
        override fun onFailure(call: Call<ResponseMode>, t: Throwable) {
            Log.i("Images" , t.localizedMessage)

            fragment.animation_view?.cancelAnimation()
            fragment.animation_view?.visibility = View.GONE

            fragment.errorView?.marginTop
            fragment.errorView?.visibility = View.VISIBLE
        }

        override fun onResponse(
            call: Call<ResponseMode>, response: Response<ResponseMode>
        ) {

            fragment.errorView?.visibility = View.GONE

            if (response.isSuccessful)
            {
                getAllImages.postValue(response.body()?.data?.filter { if (tag == "new") it.new else it.popular } )
            }
            else
            {
                getAllImages.postValue(null)
            }
        }
    })
}
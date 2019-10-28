package com.mevent.mgallery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mevent.mgallery.R
import com.mevent.mgallery.models.Data

class ImageRecyclerAdapter(val onBindviewHolderCallback: Callback.onBindviewHolderCallback) :
    RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>() {

    private var imageList: List<Data>? = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_image_layout,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {

        return imageList?.size ?: 0

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        onBindviewHolderCallback.onBindViewHolder(holder, position)

    }

    fun showAllImages(list: List<Data>) {
        imageList = list
        notifyDataSetChanged()
    }


    fun showNewImages(list: List<Data>) {
        imageList = list.filter{it.new}
        notifyDataSetChanged()
    }
    fun showPopularImages(list: List<Data>) {
        imageList = list.filter{it.popular}
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}
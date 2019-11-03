package com.mevent.mgallery.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mevent.mgallery.models.Data
import com.mevent.mgallery.ui.profile.ProfileActivity
import com.mevent.mgallery.utils.Constants


class ImageRecyclerAdapter(val onBindviewHolderCallback: Callback.onBindviewHolderCallback) :
    RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>() {

    private var imageList: List<Data>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                com.mevent.mgallery.R.layout.single_image_layout,
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

        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, ProfileActivity::class.java)
            intent.putExtra("loadURL", Constants.BASE_URL + "/media/" + imageList?.get(position)?.image?.contentUrl)
            intent.putExtra("imageName", imageList?.get(position)?.name)
            intent.putExtra("imageDescription", imageList?.get(position)?.description)
            holder.itemView.context.startActivity(intent)
        }
    }

    fun showAllImages(list: List<Data>) {
        imageList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}
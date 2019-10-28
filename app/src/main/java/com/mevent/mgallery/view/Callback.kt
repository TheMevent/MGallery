package com.mevent.mgallery.view

interface Callback {

    interface onBindviewHolderCallback {

        fun onBindViewHolder(p0: ImageRecyclerAdapter.ViewHolder, position:Int)

    }
}
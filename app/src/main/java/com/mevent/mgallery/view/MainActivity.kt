package com.mevent.mgallery.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.mevent.mgallery.models.Data
import com.mevent.mgallery.models.Image
import com.mevent.mgallery.utils.Constants
import com.mevent.mgallery.viewModels.ImagesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.single_image_layout.view.*

class MainActivity : AppCompatActivity(), Callback.onBindviewHolderCallback {

    private var mutableImageList: MutableList<Data> = mutableListOf()

    private val mAdapter by lazy { ImageRecyclerAdapter(this) }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ImagesViewModel::class.java)
    }

    var index: Int? = null

    var clickPosition: Int? = null

    var lastClicked: Int? = null


    var author: String? = ""

    var download_url: String? = ""

    var url: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mevent.mgallery.R.layout.activity_main)

        viewModel.getAllImages().observe(this, Observer {

            if (it != null && it.isNotEmpty()) {

                parentShimmerLayout.visibility = View.GONE
                parentShimmerLayout.stopShimmerAnimation()

                //Adding a dummy ImageResponseModel with visibility false at every 3rd position

                var count = 0

                it.forEach { Data ->
                    if (count % 3 == 0) {
                        mutableImageList.add(
                            Data(
                                0,
                                "",
                                "",
                                false,
                                false,
                                Image(
                                    0,
                                    "")
                            )
                        )
                        mutableImageList.add(Data)
                        count++
                    } else {
                        mutableImageList.add(Data)
                    }
                    count++
                }

                mutableImageList.add(Data(0, "", "", false,false, Image(0, "")))


            } else {
                mutableImageList = arrayListOf()
                setContentView(com.mevent.mgallery.R.layout.error_layout)
                //Toast.makeText(this, "Network Error", Toast.LENGTH_LONG).show()

            }

            mAdapter.showAllImages(mutableImageList)

        })


        val gridManager = GridLayoutManager(this, 2)

        //Setting equal padding for grid layout
        val itemDecoration = ItemOffsetDecoration(this, com.mevent.mgallery.R.dimen.padding5)
        recyclerView.addItemDecoration(itemDecoration)

        //Setting the column length of every 3rd element to 2
        gridManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                when (position % 3) {
                    0 -> return 2
                    else -> return 1
                }

            }

        }


        recyclerView.layoutManager = gridManager
        mAdapter.notifyDataSetChanged()
        recyclerView.adapter = mAdapter
        viewModel.getImagesFromNetwork()


    }


    override fun onResume() {
        super.onResume()
        parentShimmerLayout.startShimmerAnimation()


    }

    override fun onStop() {
        super.onStop()
        parentShimmerLayout.stopShimmerAnimation()
    }

   override fun onBindViewHolder(p0: ImageRecyclerAdapter.ViewHolder, position: Int) {
       val image = mutableImageList[position]

       //If the position is multiple of 3 we make their visibility GONE otherwise VISIBLE
       if (position % 3 != 0 && position != 0) {
           p0.itemView.imageView.visibility = View.VISIBLE
           Picasso.get().load(Constants.BASE_URL + "/media/" + image.image.contentUrl).resize(450, 300).centerCrop()
               .into(p0.itemView.imageView)
           /*p0.itemView.author.text = "112"
           p0.itemView.download_url.text = "Image Url  :  ${Constants.BASE_URL + "/media/" + image.image?.contentUrl}"
           p0.itemView.url.text = "Website  :  ${image.image?.contentUrl}"*/
           p0.itemView.detailsCardView.visibility = View.GONE
           p0.itemView.triangle_marker.visibility = View.GONE


       } else {
           p0.itemView.detailsCardView.visibility = View.GONE
           p0.itemView.triangle_marker.visibility = View.GONE

           clickPosition?.let {
               if ((it + 2) % 3 == 0) {
                   p0.itemView.triangle_marker.translationX = -250F


               } else if ((it + 1) % 3 == 0) {
                   p0.itemView.triangle_marker.translationX = 250F


               }
           }

           p0.itemView.author.text = ""
           p0.itemView.download_url.text = Constants.BASE_URL + "/media/" + image.image.contentUrl
           p0.itemView.url.text = Constants.BASE_URL + "/media/" + image.image.contentUrl
         /*  p0.itemView.imageName.text = image.name
           p0.itemView.imageDescription.text = image.description*/
           p0.itemView.imageView.visibility = View.GONE

       }
    }
}

package com.mevent.mgallery.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_image_layout.*


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mevent.mgallery.R.layout.single_image_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        Picasso.get().load(intent.getStringExtra("loadURL")).fit()
            .into(imageView2)

        nameText.text = intent.getStringExtra("imageName")
        description.text = intent.getStringExtra("imageDescription")

        title = ""
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
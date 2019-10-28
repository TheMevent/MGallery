package com.mevent.mgallery.ui.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mevent.mgallery.R

class NewFragment : Fragment() {

    private lateinit var newViewModel: NewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newViewModel =
            ViewModelProviders.of(this).get(NewViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_new, container, false)
        /*val textView: TextView = root.findViewById(R.layout.id..text_new)
        newViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }
}
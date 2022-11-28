package com.example.pokedex.screens.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pokedex.R


/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val aboutFragment: View = inflater.inflate(R.layout.fragment_about, container, false)

        var titletext : TextView? = aboutFragment.findViewById(R.id.aboutme_textView)
        titletext?.text = "My title text for About"

        return aboutFragment
    }


}
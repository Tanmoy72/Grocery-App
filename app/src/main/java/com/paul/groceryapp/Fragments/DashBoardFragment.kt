package com.paul.groceryapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.paul.groceryapp.R


class DashBoardFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash_board, container, false)
        val menuBackImg = view.findViewById<ImageView>(R.id.menuBackImg)
        menuBackImg.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        return view
    }


}
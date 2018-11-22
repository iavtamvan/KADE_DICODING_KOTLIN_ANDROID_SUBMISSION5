package com.iav.kade.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.iav.kade.R
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class DetailOverviewTeamFragment(private val deskripsi: String, private val id: String) : Fragment() {
    private lateinit var tvOverview: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_detail_overview_team, container, false)

        tvOverview = view.findViewById<TextView>(R.id.tv_overview)

        tvOverview.text = "" + deskripsi
        return view
    }


}

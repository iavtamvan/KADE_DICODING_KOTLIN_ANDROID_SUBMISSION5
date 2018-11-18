package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iav.kade.R
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFavoriteFragment : Fragment() {
    var adapter: FragmentPagerItemAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_home_favorite, container, false)

        adapter = FragmentPagerItemAdapter(
                activity?.supportFragmentManager, FragmentPagerItems.with(activity)
                .add("Match", MatchFavoriteFragment::class.java)
//                .add("Team", TeamFavoriteFragment::class.java)
                .create())

        val viewPager = view.findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter

        val viewPagerTab = view.findViewById<View>(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)
        return view
    }


}

package com.iav.kade.fragment


import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iav.kade.R
import com.iav.kade.adapter.ViewPagerAdapter
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class HomeFragment : Fragment() {
    internal lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager : ViewPager
    private lateinit var tabLayout : TabLayout
    internal var handler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        this.handler = Handler()
        this.handler.postDelayed(this.runnable, 1000)

        viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)

        viewTab()

        return  view
    }

    private fun viewTab(){
        viewPagerAdapter = ViewPagerAdapter(activity!!.supportFragmentManager)

        this.viewPager.adapter= viewPagerAdapter
        this.tabLayout.setupWithViewPager(viewPager)
    }

    private val runnable: Runnable = Runnable {
        viewTab()
        //            handler.postDelayed(runnable, 1000);
    }
}

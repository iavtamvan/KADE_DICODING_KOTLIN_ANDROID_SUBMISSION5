package com.iav.kade.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.iav.kade.fragment.DetailOverviewTeamFragment
import com.iav.kade.fragment.DetailPlayerTeamFragment

class ViewPagerDetailTeamAdapter(fm: FragmentManager, private val deskripsi: String, private val id: String) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        var fragment: Fragment? = null
        if (position == 0) {
            fragment = DetailOverviewTeamFragment(deskripsi, id)
        } else if (position == 1) {
            fragment = DetailPlayerTeamFragment(deskripsi, id)
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Overview"
        } else if (position == 1) {
            title = "Player"
        }
        return title
    }
}
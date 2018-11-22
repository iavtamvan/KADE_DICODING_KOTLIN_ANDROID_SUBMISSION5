package com.iav.kade.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.iav.kade.fragment.LastMatchFragment
import com.iav.kade.fragment.NextMatchFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        var fragment: Fragment? = null
        if (position == 0) {
            fragment = LastMatchFragment()
        } else if (position == 1) {
            fragment = NextMatchFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Sudah Selesai"
        } else if (position == 1) {
            title = "Akan Datang"
        }
        return title
    }
}
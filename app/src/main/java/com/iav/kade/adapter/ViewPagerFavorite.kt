package com.iav.kade.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.iav.kade.fragment.MatchFavoriteFragment
import com.iav.kade.fragment.TeamFavoriteFragment

class ViewPagerFavorite(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {

        var fragment: Fragment? = null
        if (position == 0) {
            fragment = MatchFavoriteFragment()
        } else if (position == 1) {
            fragment = TeamFavoriteFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Match"
        } else if (position == 1) {
            title = "Team"
        }
        return title
    }
}
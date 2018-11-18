package com.iav.kade

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ogaclejapan.smarttablayout.SmartTabLayout
import android.support.v4.view.ViewPager
import android.view.View
import com.iav.kade.fragment.MatchFavoriteFragment
import com.iav.kade.fragment.LastMatchFragment
import com.iav.kade.fragment.NextMatchFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                .add("Last Match", LastMatchFragment::class.java)
                .add("Next Match", NextMatchFragment::class.java)
                .add("Favorite", MatchFavoriteFragment::class.java)
                .create())

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter

        val viewPagerTab = findViewById<View>(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)
    }
}

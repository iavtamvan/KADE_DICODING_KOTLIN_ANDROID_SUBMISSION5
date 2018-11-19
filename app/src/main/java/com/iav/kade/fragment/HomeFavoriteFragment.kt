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
import com.iav.kade.adapter.ViewPagerFavorite
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFavoriteFragment : Fragment() {
    var adapter: FragmentPagerItemAdapter? = null
    internal lateinit var viewPagerAdapterTiga: ViewPagerFavorite
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    internal var handler = Handler()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_home_favorite, container, false)
        this.handler = Handler()
        this.handler.postDelayed(this.runnable, 1000)

        viewPager = view.findViewById<ViewPager>(R.id.view_pager_favorite)
        tabLayout = view.findViewById<TabLayout>(R.id.tab_layout_favorite)
        viewTabLayout()

        return view
    }
    private fun viewTabLayout(){
        viewPagerAdapterTiga = ViewPagerFavorite(activity!!.supportFragmentManager)

        this.viewPager.adapter= viewPagerAdapterTiga
        this.tabLayout.setupWithViewPager(viewPager)
    }

    private val runnable: Runnable = Runnable {
        viewTabLayout()
        //            handler.postDelayed(runnable, 1000);
    }

}

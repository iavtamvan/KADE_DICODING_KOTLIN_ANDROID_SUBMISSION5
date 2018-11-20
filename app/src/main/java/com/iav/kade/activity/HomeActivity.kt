package com.iav.kade.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.iav.kade.R
import com.iav.kade.fragment.HomeFavoriteFragment
import com.iav.kade.fragment.HomeFragment
import com.iav.kade.fragment.TeamFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.last_match -> {
                home(savedInstanceState = null)
                return@OnNavigationItemSelectedListener true
            }
            R.id.next_match -> {
                team(savedInstanceState = null)
                return@OnNavigationItemSelectedListener true
            }
            R.id.fav -> {
                fav(savedInstanceState = null)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun fav(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            val transaction = fragmentManager.beginTransaction()
            val fragment = HomeFavoriteFragment()
            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
    private fun team(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            val transaction = fragmentManager.beginTransaction()
            val fragment = TeamFragment()
            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun home(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            val fragment = HomeFragment()
            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        home(savedInstanceState)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

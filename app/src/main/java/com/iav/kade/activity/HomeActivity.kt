package com.iav.kade.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.iav.kade.fragment.FavoriteFragment
import com.iav.kade.fragment.LastMatchFragment
import com.iav.kade.fragment.NextMatchFragment
import com.iav.kade.R
import com.iav.kade.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private var menuItem: Menu? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.last_match -> {
                lastMatch(savedInstanceState = null)
                return@OnNavigationItemSelectedListener true
            }
            R.id.next_match -> {
                nextMatch(savedInstanceState = null)
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
            val fragment = FavoriteFragment()
            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun lastMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            val fragment = HomeFragment()
            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
    private fun nextMatch(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val transaction = fragmentManager.beginTransaction()
            val fragment = NextMatchFragment()
            transaction.replace(R.id.frame,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        lastMatch(savedInstanceState)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        lastMatch(savedInstanceState = null)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        menuItem = menu
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_search -> {

               true
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.iav.kade.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.iav.kade.R
import com.iav.kade.R.id.action_search
import com.iav.kade.fragment.HomeFavoriteFragment
import com.iav.kade.fragment.MatchFavoriteFragment
import com.iav.kade.fragment.HomeFragment
import com.iav.kade.fragment.TeamFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private var menuItem: Menu? = null
    private lateinit var action_search: SearchView

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
                action_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String): Boolean {
                        return false
                    }

                    override fun onQueryTextSubmit(query: String): Boolean {
//                        startActivity<SearchActivity>("KEY_SEARCH" to query, "TYPE_SEARCH" to mSelectedItemBottomNav)
                        return false
                    }

                })
                true
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iav.kade.adapter.FavoriteAdapter
import com.iav.kade.helper.Favorite

import com.iav.kade.R
import com.iav.kade.fragment.main.FavoritePresenter
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFavoriteFragment : Fragment() {
    private var favorites: ArrayList<Favorite> = arrayListOf()
    private lateinit var adapter: FavoriteAdapter
    private lateinit var rv : RecyclerView
    private lateinit var swipe : SwipeRefreshLayout

    private lateinit var presenter:FavoritePresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_favorite, container, false)
        rv = view.findViewById<RecyclerView>(R.id.rv)
        swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        getFavorite()
        return view
    }

    private fun getFavorite(){
        swipe.onRefresh {
            swipe.isRefreshing = true
            favorites.clear()
            presenter.getDatabaseFavorite()
        }
        adapter = FavoriteAdapter(activity,favorites)
        presenter = FavoritePresenter(favorites, adapter, rv, activity, swipe)
        presenter.getDatabaseFavorite()
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
        presenter.getDatabaseFavorite()
    }
}

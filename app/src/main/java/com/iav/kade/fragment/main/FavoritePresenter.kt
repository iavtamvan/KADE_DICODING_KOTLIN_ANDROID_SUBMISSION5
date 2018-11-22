package com.iav.kade.fragment.main

import android.support.v4.app.FragmentActivity
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.iav.kade.adapter.FavoriteAdapter
import com.iav.kade.helper.Favorite
import com.iav.kade.helper.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter(private var favorites: ArrayList<Favorite>,
                        private var adapter: FavoriteAdapter,
                        private var recyclerView: RecyclerView,
                        private var context: FragmentActivity?,
                        private var swipe: SwipeRefreshLayout) {

    fun getDatabaseFavorite() {
        context?.database?.use {
            swipe.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.clear()
            favorites.addAll(favorite)
            if (favorite.size != 0) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                adapter = FavoriteAdapter(context, favorites)
                recyclerView.adapter = adapter
//                adapter.notifyDataSetChanged()
            } else {
            }
        }
    }
}
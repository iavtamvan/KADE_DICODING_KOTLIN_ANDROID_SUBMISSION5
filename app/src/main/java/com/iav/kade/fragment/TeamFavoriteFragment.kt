package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iav.kade.R
import com.iav.kade.adapter.TeamFavoriteAdapter
import com.iav.kade.helper.TeamFavorit
import com.iav.kade.helper.databaseTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamFavoriteFragment : Fragment() {
    private var favoritesTeam: ArrayList<TeamFavorit> = arrayListOf()
    private lateinit var adapter: TeamFavoriteAdapter
    private lateinit var listFavorite : RecyclerView
    private lateinit var swipe : SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_team_favorite, container, false)
        listFavorite = view.findViewById<RecyclerView>(R.id.rv)
        swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)

        getFavorite()
        return view
    }
    private fun getFavorite(){
        swipe.onRefresh {
            swipe.isRefreshing = true
            favoritesTeam.clear()
            getFavorite()
        }
        context?.databaseTeam?.use {
            swipe.isRefreshing = false
            val result = select(TeamFavorit.TABLE_TEAM)
            val favorite = result.parseList(classParser<TeamFavorit>())
            favoritesTeam.clear()
            favoritesTeam.addAll(favorite)
            if (favorite.size != 0){
                listFavorite.layoutManager = LinearLayoutManager(context)
                adapter = TeamFavoriteAdapter(activity,favoritesTeam)
                listFavorite.adapter = adapter
                adapter.notifyDataSetChanged()
            }else{
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getFavorite()
        favoritesTeam.isEmpty()
        favoritesTeam.clear()
        adapter = TeamFavoriteAdapter(activity,favoritesTeam)
        adapter.notifyDataSetChanged()
    }

}

package com.iav.kade.fragment.main

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.iav.kade.adapter.TeamAdapter
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class TeamPresenter(private var items: ArrayList<Item> = arrayListOf(),
                    private val context: FragmentActivity?,
                    private var rv: RecyclerView,
                    private var mAdapter: TeamAdapter) {

    fun getTeam(id:String) {
        val service: ApiService = RetroConfig.provideApi()
        service.getTeamByLigaId(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    items.clear()
                    items = result.teams as ArrayList<Item>
                    rv.layoutManager = LinearLayoutManager(context)
                    mAdapter = TeamAdapter(context, items)
                    rv.adapter = mAdapter
                    //                    rv.layoutManager= LinearLayoutManager(activity)
//                    mAdapter = TeamAdapter(activity,item)
//                    rv.adapter = mAdapter
//
//                    search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                        override fun onQueryTextChange(newText: String): Boolean {
//                            //panggil API Search
//                            mAdapter?.filter?.filter(newText.toLowerCase())
//                            return false
//                        }
//
//                        override fun onQueryTextSubmit(query: String): Boolean {
//                            //Task HERE
//                            return false
//                        }
//
//                    })
                }, { error ->
                })
    }

}
package com.iav.kade.fragment.main

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.iav.kade.adapter.NextMatchAdapter
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class NextMatchPresenter(private var items: ArrayList<Item> = arrayListOf(),
                         private val context: FragmentActivity?,
                         private var rv: RecyclerView,
                         private var mAdapter: NextMatchAdapter) {

    fun getNextMatch(id:String) {
        val service: ApiService = RetroConfig.provideApi()
        service.getNextMatch(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    items.clear()
                    items = it.events as ArrayList<Item>
                    rv.layoutManager = LinearLayoutManager(context)
                    mAdapter = NextMatchAdapter(context, items)
                    rv.adapter = mAdapter
                }, {
                    error("errror" + it.localizedMessage)
                })
    }
}
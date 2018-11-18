package com.iav.kade.fragment.main

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.iav.kade.adapter.LastMatchAdapter
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LastMatchPresenter(private var items: ArrayList<Item> = arrayListOf(),
                         private val context: FragmentActivity?,
                         private var rv: RecyclerView,
                         private var mAdapter: LastMatchAdapter) {
    fun getLastMatch(id:String) {
        val service: ApiService
        service = RetroConfig.provideApi()
        service.getLastMatch(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    items.clear()
                    items = it.events as ArrayList<Item>
                    rv.layoutManager = LinearLayoutManager(context)
                    mAdapter = LastMatchAdapter(context, items)
                    rv.adapter = mAdapter
                }, {
                    error("errror" + it.localizedMessage)
                })
    }

}
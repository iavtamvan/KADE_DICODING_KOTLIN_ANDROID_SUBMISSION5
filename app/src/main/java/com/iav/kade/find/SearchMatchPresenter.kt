package com.iav.kade.find

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.iav.kade.adapter.NextMatchAdapter
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchMatchPresenter(private var items: ArrayList<Item> = arrayListOf(),
                           private val context: FragmentActivity?, private var rv: RecyclerView,
                           private var mAdapter: NextMatchAdapter) {

    fun eventMatchSearch(id:String) {
        val service: ApiService = RetroConfig.provideApi()
        service.searchEvent(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    items.clear()
                    items = it.event as ArrayList<Item>
                    rv.layoutManager = LinearLayoutManager(context)
                    mAdapter = NextMatchAdapter(context, items)
                    rv.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }, {
                    error("errror" + it.localizedMessage)
                })
    }
}
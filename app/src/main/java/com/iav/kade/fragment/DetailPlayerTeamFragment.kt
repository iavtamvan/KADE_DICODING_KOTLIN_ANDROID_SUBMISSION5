package com.iav.kade.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iav.kade.R
import com.iav.kade.adapter.PlayerTeamAdapter
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import kotlinx.android.synthetic.main.fragment_detail_player_team.*
import org.jetbrains.anko.support.v4.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 *
 */
@SuppressLint("ValidFragment")
class DetailPlayerTeamFragment(private var deskripsi:String, private var id: String) : Fragment() {
    private var itemku : ArrayList<Item> = arrayListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_detail_player_team, container, false)
        getData()

        return view
    }
    private fun getData() {
        val service: ApiService = RetroConfig.provideApi()
        service.playerByIdTeam(""+id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            itemku.clear()
                            itemku = result.player as ArrayList<Item>
                            rv.layoutManager = LinearLayoutManager(activity)
                            rv.adapter = PlayerTeamAdapter(activity, itemku)

                        },
                        { error ->
                            toast(""+error.message) }
                )
    }

}

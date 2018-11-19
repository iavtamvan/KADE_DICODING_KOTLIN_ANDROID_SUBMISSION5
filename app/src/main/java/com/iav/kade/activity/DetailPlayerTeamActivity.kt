package com.iav.kade.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.model.Item
import com.iav.kade.rest.ApiService
import com.iav.kade.rest.RetroConfig
import kotlinx.android.synthetic.main.activity_detail_player_team.*
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class DetailPlayerTeamActivity : AppCompatActivity() {
    private var items :MutableList<Item> = mutableListOf()
    private var list: ArrayList<Item> = arrayListOf()
    var posisi =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player_team)
        getData()
    }

    private fun getData(){
        list = intent.getParcelableArrayListExtra("list")
        posisi = intent.getStringExtra("posisi").toInt()

        val service: ApiService = RetroConfig.provideApi()
        service.detailPlayer(""+list.get(posisi).idPlayer)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.players as MutableList<Item>
                            Glide.with(applicationContext)
                                    .load(items.get(0).fotoPlayer)
                                    .into(img_player)
                            tv_height.text = items.get(0).tinggiPlaey
                            tv_weight.text = items.get(0).beratPlayer
                            tv_posisi.text = items.get(0).posisiPlayer
                            tv_deskripsi.text = items.get(0).deskripsi

                        },
                        { error ->
                            toast(""+error.message) }
                )
    }
}

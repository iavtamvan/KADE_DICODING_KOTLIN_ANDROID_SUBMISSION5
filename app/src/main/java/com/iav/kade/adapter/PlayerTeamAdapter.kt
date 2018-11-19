package com.iav.kade.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.activity.DetailPlayerTeamActivity
import com.iav.kade.model.Item
import kotlinx.android.synthetic.main.list_player.view.*

class PlayerTeamAdapter(private val contex: Context?, private val items: ArrayList<Item>) : RecyclerView.Adapter<PlayerTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            ViewHolder(LayoutInflater.from(contex).inflate(R.layout.list_player, p0, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama.text = items.get(position).namaPlayer
        holder.posisiPlayer.text = items.get(position).posisiPlayer
        Glide.with(contex)
                .load(items.get(position).fotoPlayer).into(holder.image)

        holder.itemView.setOnClickListener({
            val intent = Intent(contex, DetailPlayerTeamActivity::class.java)
            intent.putParcelableArrayListExtra("list", items)
            intent.putExtra("posisi", holder.adapterPosition.toString())
            contex?.startActivity(intent)
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.tv_nama_player
        val image = view.img_player
        val posisiPlayer = view.tv_posisi
    }
}
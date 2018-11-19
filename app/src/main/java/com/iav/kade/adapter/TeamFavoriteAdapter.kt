package com.iav.kade.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.activity.DetailTeamFavoriteActivity
import com.iav.kade.helper.TeamFavorit
import kotlinx.android.synthetic.main.list_team.view.*

class TeamFavoriteAdapter(private val contex: Context?, private val items: ArrayList<TeamFavorit>) : RecyclerView.Adapter<TeamFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            ViewHolder(LayoutInflater.from(contex).inflate(R.layout.list_team, p0, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama.text = items.get(position).teamName
        Glide.with(contex)
                .load(items.get(position).teamImage).into(holder.image)

        holder.itemView.setOnClickListener({
            val intent = Intent(contex, DetailTeamFavoriteActivity::class.java)
            intent.putParcelableArrayListExtra("list", items)
            intent.putExtra("posisi", holder.adapterPosition.toString())
            intent.putExtra("id", "${items.get(position).teamId}")
            contex?.startActivity(intent)
        })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.tv_name_team
        val image = view.img_team
    }
}
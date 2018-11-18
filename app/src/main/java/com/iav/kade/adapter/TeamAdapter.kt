package com.iav.kade.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.activity.DetailActivity
import com.iav.kade.activity.DetailTeamActivity
import com.iav.kade.model.Item
import kotlinx.android.synthetic.main.list_team.view.*


class TeamAdapter : RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private lateinit var context: Context
    private var items:ArrayList<Item>? = null
    private var orig:ArrayList<Item>? = null
//    private var recycleFilter: RecycleFilter? = null

    constructor(context: Context?, items: ArrayList<Item>) : this() {
        this.context = context!!
        this.items = items
        this.orig = items
    }

    constructor()
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_team,p0,false))

    override fun getItemCount(): Int = items?.size as Int

//    override fun getFilter(): Filter {
//        if (recycleFilter == null){
//            recycleFilter = RecycleFilter()
//        }
//        return recycleFilter as RecycleFilter
//    }
//
//    inner class RecycleFilter :Filter(){
//        override fun performFiltering(charSequence: CharSequence?):  FilterResults {
//            var results2: FilterResults = FilterResults()
//            if (charSequence != null && charSequence.length > 0) {
//                var locallist: ArrayList<Item> = ArrayList<Item>()
//                for (i: Int in 0..orig?.size?.minus(1) as Int) {
//                    if (orig?.get(i)?.teamName?.toLowerCase()?.contains(charSequence.toString().toLowerCase()) as Boolean) {
//                        locallist.add(orig?.get(i) as Item)
//                    }
//                }
//                results2.values = locallist
//                results2.count = locallist.size as Int
//            } else {
//                results2.values = orig
//                results2.count = orig?.size as Int
//            }
//
//            return results2
//        }
//
//        override fun publishResults(charSequence: CharSequence, filterResults:FilterResults?) {
//            items = filterResults?.values as ArrayList<Item>
//            notifyDataSetChanged()
//        }
//
//    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nama.text = items?.get(position)?.teamName
        Glide.with(context)
                .load(items?.get(position)?.teamBadge).into(holder.image)
    holder.itemView.setOnClickListener({
        val intent = Intent(context, DetailTeamActivity::class.java)
        intent.putParcelableArrayListExtra("list",items)
        intent.putExtra("posisi", holder.adapterPosition.toString())
        intent.putExtra("id" , "${items?.get(position)?.teamId}")
        context?.startActivity(intent)
    })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nama = view.tv_name_team
        val image = view.img_team
    }
}
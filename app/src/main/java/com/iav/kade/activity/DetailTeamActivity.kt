package com.iav.kade.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.fragment.DetailOverviewTeamFragment
import com.iav.kade.fragment.DetailPlayerTeamFragment
import com.iav.kade.helper.TeamFavorit
import com.iav.kade.helper.databaseTeam
import com.iav.kade.model.Item
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.util.ArrayList

class DetailTeamActivity : AppCompatActivity() {
    var adapter: FragmentPagerItemAdapter? = null

    lateinit var posisi:String
    private var list: ArrayList<Item> = arrayListOf()
    private var menuItem: Menu? = null
    private var nilai:String = "not"
    private lateinit var deskripsi:String
    private lateinit var idTeam:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        adapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                .add("Overview", DetailOverviewTeamFragment::class.java)
                .add("Player", DetailPlayerTeamFragment::class.java)
                .create())

        val viewPager = findViewById<View>(R.id.viewpager) as ViewPager
        viewPager.adapter = adapter

        val viewPagerTab = findViewById<View>(R.id.viewpagertab) as SmartTabLayout
        viewPagerTab.setViewPager(viewPager)

        loadData()
    }

    private fun loadData() {
        favoriteState()

        list = intent.getParcelableArrayListExtra("list")
        posisi = intent.getStringExtra("posisi")

        deskripsi = list.get(posisi.toInt()).deskripsi.toString()
        idTeam = list.get(posisi.toInt()).teamId.toString()

        tv_name_team.text = list.get(posisi.toInt()).teamName
        tv_tahun.text = list.get(posisi.toInt()).tahunBerdiri
        tv_lapangan.text = list.get(posisi.toInt()).lapangan
        Glide.with(applicationContext)
                .load(list.get(posisi.toInt()).teamBadge)
                .into(img_team)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (nilai.equals("favorit")){
                    removeFromFavorite()
                    setFavorite(nilai)
                    true
                }
                else {
                    addToFavorite()
                    setFavorite(nilai)
                    true
                }
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            databaseTeam.use {
                insert(TeamFavorit.TABLE_TEAM,
                        TeamFavorit.TEAM_ID to idTeam,
                        TeamFavorit.TEAM_IMAGE to list.get(posisi.toInt()).teamBadge,
                        TeamFavorit.TEAM_NAME to list.get(posisi.toInt()).teamName,
                        TeamFavorit.TAHUN_BERDIRI to list.get(posisi.toInt()).tahunBerdiri,
                        TeamFavorit.LAPANGAN to list.get(posisi.toInt()).lapangan,
                        TeamFavorit.DESKRIPIS to deskripsi)
            }
            toast("added to favorite")

            nilai = "favorit"

        } catch (e: SQLiteConstraintException){
            toast(""+e.localizedMessage)

        }
    }
    private fun removeFromFavorite(){
        try {
            databaseTeam.use {
                delete(TeamFavorit.TABLE_TEAM, "(TEAM_ID = {id})",
                        "id" to list.get(posisi.toInt()).teamId.toString())
            }
            toast("removed to favorite")

            nilai = "not"
        } catch (e: SQLiteConstraintException){
            toast(""+e.localizedMessage)
        }
    }
    private fun setFavorite(pilihan:String) {
        if (pilihan.equals("not")) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        } else if (pilihan.equals("favorit")) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        }
    }

    private fun favoriteState(){
        list = intent.getParcelableArrayListExtra("list")
        databaseTeam.use {
            val result = select(TeamFavorit.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to intent.getStringExtra("id"))
            val favorite = result.parseList(classParser<TeamFavorit>())
            if (favorite.size != 0){
                nilai = "favorit"

                setFavorite(nilai)
            }else{
                nilai = "not"

                setFavorite(nilai)

            }
        }
    }
}

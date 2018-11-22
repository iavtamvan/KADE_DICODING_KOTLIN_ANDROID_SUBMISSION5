package com.iav.kade.activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.iav.kade.R
import com.iav.kade.adapter.ViewPagerDetailTeamAdapter
import com.iav.kade.helper.TeamFavorit
import com.iav.kade.helper.databaseTeam
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.nio.file.Files.delete
import java.util.ArrayList

class DetailTeamFavoriteActivity : AppCompatActivity() {
    internal lateinit var viewPagerAdapter: ViewPagerDetailTeamAdapter
    private lateinit var viewPager : ViewPager
    private lateinit var tabLayout : TabLayout
    lateinit var posisi:String
    private var list: ArrayList<TeamFavorit> = arrayListOf()
    private var menuItem: Menu? = null
    private var nilai:String = "not"
    private lateinit var deskripsi:String
    private lateinit var idTeam:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team_favorite)
        awal()
    }


    private fun awal(){
        favoriteState()

        list = intent.getParcelableArrayListExtra("list")
        posisi = intent.getStringExtra("posisi")

        deskripsi = list.get(posisi.toInt()).deskripsi.toString()
        idTeam = list.get(posisi.toInt()).teamId.toString()

        posisi = intent.getStringExtra("posisi")
        viewPager = findViewById<ViewPager>(R.id.view_pager)
        tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        viewPagerAdapter = ViewPagerDetailTeamAdapter(supportFragmentManager,deskripsi,idTeam)

        this.viewPager.adapter= viewPagerAdapter
        this.tabLayout.setupWithViewPager(viewPager)

        tv_name_team.text = list.get(posisi.toInt()).teamName
        tv_tahun.text = list.get(posisi.toInt()).tahunBerdiri
        tv_lapangan.text = list.get(posisi.toInt()).lapangan
        Glide.with(applicationContext)
                .load(list.get(posisi.toInt()).teamImage)
                .into(img_team)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav, menu)
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
                    setFavorite()
                    true
                }
                else {
                    addToFavorite()
                    setFavorite()
                    true
                }
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (nilai.equals("not"))
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
    }

    private fun addToFavorite(){
        try {
            databaseTeam.use {
                insert(TeamFavorit.TABLE_TEAM,
                        TeamFavorit.TEAM_ID to list.get(posisi.toInt()).teamId,
                        TeamFavorit.TEAM_IMAGE to list.get(posisi.toInt()).teamImage,
                        TeamFavorit.TEAM_NAME to list.get(posisi.toInt()).teamName,
                        TeamFavorit.TAHUN_BERDIRI to list.get(posisi.toInt()).tahunBerdiri,
                        TeamFavorit.LAPANGAN to list.get(posisi.toInt()).lapangan,
                        TeamFavorit.DESKRIPIS to list.get(posisi.toInt()).deskripsi)
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

    private fun favoriteState(){
        list = intent.getParcelableArrayListExtra("list")
        databaseTeam.use {
            val result = select(TeamFavorit.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to intent.getStringExtra("id"))
            val favorite = result.parseList(classParser<TeamFavorit>())
            if (favorite.size != 0){
                nilai = "favorit"

                setFavorite()
            }else{
                nilai = "not"

                setFavorite()

            }
        }
    }
}

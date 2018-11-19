package com.iav.kade.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.iav.kade.R

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        var keySearch = intent.getStringExtra("KEY_SEARCH")
        var typeSearch = intent.getIntExtra("TYPE_SEARCH",0)


        val mFragmentList = mutableListOf<Fragment>()
//        mFragmentList.add(FragSchedules.newInstance(3,keySearch))
//        mFragmentList.add(FragClubs.newInstance(3,keySearch))

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_search, mFragmentList[typeSearch], mFragmentList[typeSearch]::class.simpleName)
                .commit()

    }
}

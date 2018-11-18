package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.iav.kade.R
import com.iav.kade.adapter.TeamAdapter
import com.iav.kade.fragment.main.TeamPresenter
import com.iav.kade.model.Item

class TeamFragment : Fragment() {
    var items: ArrayList<Item> = arrayListOf()
    lateinit var presenter:TeamPresenter
    lateinit var adapter: TeamAdapter
    lateinit var rv:RecyclerView
    private lateinit var spinner :Spinner
    var list_of_items = arrayOf("English Premier League", "German Bundesliga", "Spanish La Liga")
    private  var idTeam="4328"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_team, container, false)
        spinner = view.findViewById<Spinner>(R.id.spinner)
        rv= view.findViewById(R.id.rv)
        spinnerData()
        return view
    }

    fun spinnerData() {
        val aa = ArrayAdapter(activity, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var data = spinner.selectedItem.toString()
                if (data.equals("English Premier League")) {
                    idTeam = "4328"
                    loadData()
                } else if (data.equals("German Bundesliga")) {
                    idTeam = "4331"
                    loadData()
                } else if (data.equals("Spanish La Liga")) {
                    idTeam = "4335"
                    loadData()
                }
            }

        }
        loadData()
    }
    private fun loadData() {
        adapter= TeamAdapter(activity, items)
        presenter = TeamPresenter(items, activity, rv, adapter)
        presenter.getTeam(idTeam)
    }

}

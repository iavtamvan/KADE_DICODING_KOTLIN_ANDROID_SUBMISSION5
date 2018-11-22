package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import com.iav.kade.R
import com.iav.kade.adapter.TeamAdapter
import com.iav.kade.find.SearchTeamPresenter
import com.iav.kade.fragment.main.TeamPresenter
import com.iav.kade.model.Item

class TeamFragment : Fragment() {
    var items: ArrayList<Item> = arrayListOf()
    lateinit var presenter:TeamPresenter
    lateinit var adapter: TeamAdapter
    lateinit var rv:RecyclerView
    private lateinit var spinner :Spinner
    var list_of_items = arrayOf("English Premier League", "German Bundesliga", "Spanish La Liga", "English League Championship", "Scottish Premier League", "Italian Serie A"
            , "French Ligue 1", "Mexican Primera League")
    private  var idTeam="4328"

    private lateinit var presenterTeamSearch:SearchTeamPresenter
    private lateinit var searchView: SearchView
    var query = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_team, container, false)
        spinner = view.findViewById<Spinner>(R.id.spinner)
        rv= view.findViewById(R.id.rv)
        spinnerData()
        setHasOptionsMenu(true)
        return view
    }

    fun spinnerData() {
        val aa = ArrayAdapter(activity, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.setAdapter(aa)
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var data = spinner.selectedItem.toString()
                if (data.equals("English Premier League")) {
                    idTeam = "4328"
                    presenter.getTeam(idTeam)
                } else if (data.equals("German Bundesliga")) {
                    idTeam = "4331"
                    presenter.getTeam(idTeam)
                } else if (data.equals("Spanish La Liga")) {
                    idTeam = "4335"
                    presenter.getTeam(idTeam)
                } else if (data.equals("English League Championship")) {
                    idTeam = "4329"
                    presenter.getTeam(idTeam)
                } else if (data.equals("Scottish Premier League")) {
                    idTeam = "4330"
                    presenter.getTeam(idTeam)
                } else if (data.equals("Italian Serie A")) {
                    idTeam = "4332"
                    presenter.getTeam(idTeam)
                } else if (data.equals("French Ligue 1")) {
                    idTeam = "4334"
                    presenter.getTeam(idTeam)
                } else if (data.equals("Mexican Primera League")) {
                    idTeam = "4350"
                    presenter.getTeam(idTeam)
                }
            }

        }
        loadData()
    }
    private fun loadData() {
        adapter= TeamAdapter(activity, items)
        presenter = TeamPresenter(items, activity, rv, adapter)
        presenterTeamSearch = SearchTeamPresenter(items, activity, rv, adapter)
        presenterTeamSearch.eventTeamSearch(query)
    }



    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater?) {
        menuInflater?.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
//        searchView.setIconifiedByDefault(false)
//        searchView.isIconified = false
        searchView.requestFocusFromTouch()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                if (text?.length!! > 2) {
//                if (text != null) {
                    presenterTeamSearch.eventTeamSearch(text)
                    adapter.notifyDataSetChanged()
                } else{
                    loadData()
                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }
        })
    }
}

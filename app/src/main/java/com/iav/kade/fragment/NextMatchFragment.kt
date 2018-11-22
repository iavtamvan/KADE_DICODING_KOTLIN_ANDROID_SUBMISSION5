package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.*
import com.example.cia.footballschedule.utils.invisible
import com.example.cia.footballschedule.utils.visible
import com.iav.kade.R
import com.iav.kade.adapter.NextMatchAdapter
import com.iav.kade.find.SearchMatchPresenter
import com.iav.kade.fragment.main.MainView
import com.iav.kade.fragment.main.NextMatchPresenter
import com.iav.kade.model.Item
import kotlinx.android.synthetic.main.fragment_last_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class NextMatchFragment : Fragment(), MainView {
    private var items: ArrayList<Item> = arrayListOf()
    lateinit var mAdapter: NextMatchAdapter
    private lateinit var spinner: Spinner
    lateinit var rv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: NextMatchPresenter
    private lateinit var presenterSearchMatch: SearchMatchPresenter
    private lateinit var progress_circular: ProgressBar
    private var idTeam = "4328"
    var list_of_items = arrayOf("English Premier League", "German Bundesliga", "Spanish La Liga", "English League Championship", "Scottish Premier League", "Italian Serie A"
            , "French Ligue 1", "Mexican Primera League")

    private lateinit var searchView: SearchView
    var query = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_next_match, container, false)
        rv = view.findViewById(R.id.rv)
        spinner = view.findViewById<Spinner>(R.id.spinner)
        progress_circular = view.findViewById(R.id.progress_circular)
        spinnerData()
        setHasOptionsMenu(true)
        return view
    }

//    override fun onResume() {
//        super.onResume()
//        loadData()
//    }

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
                }else if (data.equals("English League Championship")) {
                    idTeam = "4329"
                    loadData()
                }else if (data.equals("Scottish Premier League")) {
                    idTeam = "4330"
                    loadData()
                }else if (data.equals("Italian Serie A")) {
                    idTeam = "4332"
                    loadData()
                }else if (data.equals("French Ligue 1")) {
                    idTeam = "4334"
                    loadData()
                }else if (data.equals("Mexican Primera League")) {
                    idTeam = "4350"
                    loadData()
                }
            }

        }
        loadData()
    }


    fun loadData(){
        progress_circular.invisible()
        mAdapter = NextMatchAdapter(activity, items)
        presenter = NextMatchPresenter(items, activity, rv, mAdapter)
        presenterSearchMatch = SearchMatchPresenter(items, activity, rv, mAdapter)
        presenter.getNextMatch(idTeam)
    }

    override fun progressShow() {
        progress_circular.visible()
    }

    override fun progessHide() {
        progress_circular.invisible()
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
                    presenterSearchMatch.eventMatchSearch(text)
                    mAdapter.notifyDataSetChanged()
                } else{
                    loadData()
                }
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {

//                }
                return false
            }
        })
    }
}

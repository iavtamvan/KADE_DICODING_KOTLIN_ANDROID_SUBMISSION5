package com.iav.kade.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.cia.footballschedule.utils.invisible
import com.example.cia.footballschedule.utils.visible
import com.iav.kade.R
import com.iav.kade.adapter.LastMatchAdapter
import com.iav.kade.fragment.main.LastMatchPresenter
import com.iav.kade.fragment.main.MainView
import com.iav.kade.model.Item
import kotlinx.android.synthetic.main.fragment_last_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MainView {

    private var items: ArrayList<Item> = arrayListOf()
    private lateinit var mAdapter: LastMatchAdapter
    private lateinit var spinner: Spinner
    private lateinit var rv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: LastMatchPresenter
    private var idTeam = "4328"
    var list_of_items = arrayOf("English Premier League", "German Bundesliga", "Spanish La Liga")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_last_match, container, false)
        rv = view.findViewById(R.id.rv)
        progressBar = view.findViewById(R.id.progress_circular)
        spinner = view.findViewById<Spinner>(R.id.spinner)
        spinnerData()
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
                }
            }

        }
        loadData()
    }

    fun loadData() {

        mAdapter = LastMatchAdapter(activity, items)
        presenter = LastMatchPresenter(items, activity, rv, mAdapter)
        presenter.getLastMatch(idTeam)
    }

    override fun progressShow() {
        progress_circular.visible()
    }

    override fun progessHide() {
        progress_circular.invisible()
    }


}
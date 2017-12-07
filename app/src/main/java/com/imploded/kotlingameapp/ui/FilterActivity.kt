package com.imploded.kotlingameapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.adapters.FilterAdapter
import com.imploded.kotlingameapp.interfaces.OnItemCheckedListener
import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.viewmodels.FilterViewModel
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {

    companion object {
        val FilterPlatformId = "FilterPlatformId"
        val ActiveFilterId = "ActiveFilterId"
    }

    private val viewModel = FilterViewModel()

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val allPlatforms = intent.getStringExtra(FilterPlatformId)
        val activeFilter = intent.getStringExtra(ActiveFilterId)
        viewModel.restoreActiveFilter(allPlatforms, activeFilter)

        recyclerView = filter_list
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = FilterAdapter(viewModel.filterItems, object: OnItemCheckedListener{
            override fun invoke(filter: FilterItem, checked: Boolean) {
            }

        })

        filterButton.setOnClickListener{
            val result = Intent(this, MainActivity::class.java)
            result.putExtra(FilterPlatformId, viewModel.getFilterItemsAsJson())
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}

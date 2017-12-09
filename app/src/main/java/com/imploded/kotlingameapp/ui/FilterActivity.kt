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
import com.imploded.kotlingameapp.viewmodels.FilterViewModel
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {

    companion object {
        val FilterAllPlatformsId = "FilterAllPlatformsId"
        val ActiveFilterId = "ActiveFilterId"
    }

    private val viewModel = FilterViewModel()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val allPlatforms = intent.getStringExtra(FilterAllPlatformsId)
        val activeFilter = intent.getStringExtra(ActiveFilterId)
        viewModel.restoreActiveFilter(allPlatforms, activeFilter)

        recyclerView = filter_list
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = FilterAdapter(viewModel.filterItems, {
            item, checked -> Log.d("FilterActivity", "$item checkedStateChanged: $checked")}
        )

        filterButton.setOnClickListener{
            val result = Intent(this, MainActivity::class.java)
            result.putExtra(ActiveFilterId, viewModel.getFilterItemsAsJson())
            setResult(Activity.RESULT_OK, result)
            finish()
        }

        allButton.setOnClickListener {
            viewModel.selectAll()
            recyclerView.adapter.notifyDataSetChanged()
        }
        noneButton.setOnClickListener{
            viewModel.selectNone()
            recyclerView.adapter.notifyDataSetChanged()
        }
    }
}

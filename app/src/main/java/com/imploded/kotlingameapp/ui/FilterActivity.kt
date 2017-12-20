package com.imploded.kotlingameapp.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.adapters.FilterAdapter
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.AppConstants
import com.imploded.kotlingameapp.utils.fromJson
import com.imploded.kotlingameapp.viewmodels.FilterViewModel
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import java.net.URL

class FilterActivity : AppCompatActivity() {

    companion object {
        val FilterAllPlatformsId = "FilterAllPlatformsId"
        val ActiveFilterId = "ActiveFilterId"
    }

    private val viewModel = FilterViewModel()

    private lateinit var recyclerView: RecyclerView

    fun getGames() : List<Game> {
        val url = AppConstants.GetGamesUrl
        val json = URL(url).readText()
        return Gson().fromJson<List<Game>>(json).sortedBy { game -> game.name }
    }

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

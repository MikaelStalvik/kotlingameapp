package com.imploded.kotlingameapp.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.viewmodels.MainViewModel
import com.imploded.kotlingameapp.adapters.GamesAdapter
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.MainRepository
import com.imploded.kotlingameapp.utils.consume
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    companion object {
        val RequestSortCode = 303
        val RequestFilterCode = 304
    }

    private val repo = MainRepository()
    private val viewModel = MainViewModel(repo)
    private lateinit var recyclerView: RecyclerView

    private fun openDetail(game: Game) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, game.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.game_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            viewModel.getGamesForView()
            uiThread {
                updateView(viewModel.activeSorting)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_sorting -> consume { showSortingView()}
        R.id.action_filter -> consume { showFilterView() }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                RequestSortCode -> {
                    updateView(data?.getStringExtra(SortingActivity.SortingId).toString())
                }
                RequestFilterCode -> {
                    updateViewWithFilter(data?.getStringExtra(FilterActivity.ActiveFilterId).toString())
                }
                else -> super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private fun updateView(sorting: String) {
        viewModel.activeSorting = sorting
        val adapter = GamesAdapter(viewModel.getGamesForView(), {openDetail(it)})
        recyclerView.adapter = adapter
    }

    private fun updateViewWithFilter(filter: String) {
        viewModel.activeFilter = filter
        updateView(viewModel.activeSorting)
    }

    private fun showSortingView() {
        startActivityForResult<SortingActivity>(
                RequestSortCode,
                SortingActivity.SortingId to viewModel.activeSorting
        )
    }

    private fun showFilterView() {
        val json = Gson().toJson(viewModel.getAllPlatforms())
        startActivityForResult<FilterActivity>(
                RequestFilterCode,
                FilterActivity.FilterAllPlatformsId to json,
                FilterActivity.ActiveFilterId to viewModel.activeFilter
        )
    }


}

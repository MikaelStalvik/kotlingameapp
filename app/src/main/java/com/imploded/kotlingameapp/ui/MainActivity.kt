package com.imploded.kotlingameapp.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.viewmodels.MainViewModel
import com.imploded.kotlingameapp.adapters.GamesAdapter
import com.imploded.kotlingameapp.interfaces.OnItemClickListener
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.consume
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    companion object {
        val RequestSortCode = 303
        lateinit var recyclerView: RecyclerView
            private set
    }
    private val viewModel = MainViewModel()

    private fun openDetail(game: Game) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, game.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.game_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            val games = viewModel.getGamesForView()
            uiThread {
                val adapter = GamesAdapter(games, object: OnItemClickListener{
                    override fun invoke(game: Game) {
                        openDetail(game)
                    }
                })
                recyclerView.adapter = adapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_sorting -> consume { sortingView()}
        R.id.action_filter -> consume { filterView() }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                RequestSortCode -> {
                    updateSorting(data?.getStringExtra(SortingActivity.SortingId).toString())
                }
                else -> super.onActivityResult(requestCode, resultCode, data)

            }
        }
    }

    fun updateSorting(sorting: String) {
        viewModel.activeSorting = viewModel.translateSortingArgument(sorting)
         val adapter = GamesAdapter(viewModel.getGamesForView(), object: OnItemClickListener{
            override fun invoke(game: Game) {
                openDetail(game)
            }
        })
        recyclerView.adapter = adapter
    }
    fun sortingView() {
        val intent = Intent(this, SortingActivity::class.java)
        startActivityForResult(intent, RequestSortCode)
    }

    fun filterView() {

    }


}

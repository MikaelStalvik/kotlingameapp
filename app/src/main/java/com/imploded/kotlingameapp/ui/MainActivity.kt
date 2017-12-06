package com.imploded.kotlingameapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.viewmodels.MainViewModel
import com.imploded.kotlingameapp.adapters.GamesAdapter
import com.imploded.kotlingameapp.interfaces.OnItemClickListener
import com.imploded.kotlingameapp.model.Game
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    private fun openDetail(game: Game) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ID, game.id)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.game_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            val games = viewModel.games
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

}

package com.imploded.kotlingameapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.ViewModels.MainViewModel
import com.imploded.kotlingameapp.adapters.GamesAdapter
import com.imploded.kotlingameapp.interfaces.OnItemClickListener
import com.imploded.kotlingameapp.model.Game
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    companion object {
        val ID = "MainActivity:id"
    }

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var recyclerView = findViewById<RecyclerView>(R.id.game_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        doAsync {
            val games = viewModel.getGames()
            uiThread {
                val adapter = GamesAdapter(games, object: OnItemClickListener{
                    override fun invoke(game: Game) {
                        toast(game.name)
                    }
                })
                recyclerView.adapter = adapter
            }
        }
    }

}

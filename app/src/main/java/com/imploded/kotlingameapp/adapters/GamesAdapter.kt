package com.imploded.kotlingameapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.interfaces.OnItemClickListener
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.load
import kotlinx.android.synthetic.main.row_game.view.*

/**
 * Created by Mikael on 2017-12-03.
 */
class GamesAdapter(private val games: List<Game>, private val itemClick: OnItemClickListener) : RecyclerView.Adapter<GamesAdapter.GameHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_game, parent, false)
        return GameHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bindGame(games[position])
    }

    override fun getItemCount(): Int = games.size

    class GameHolder(view: View, private val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {

        fun bindGame(game: Game) {
            with(game) {
                itemView.imageViewPicture.load(picture)
                itemView.textViewTitle.text = name
                itemView.textViewInfo.text = "$releaseYear, $publisher"
                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }
}

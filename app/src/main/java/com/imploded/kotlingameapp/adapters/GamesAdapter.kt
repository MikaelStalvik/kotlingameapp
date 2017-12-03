package com.imploded.kotlingameapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.interfaces.OnItemClickListener
import com.imploded.kotlingameapp.model.Game
import com.squareup.picasso.Picasso

/**
 * Created by Mikael on 2017-12-03.
 */
class GamesAdapter(private val games: List<Game>, private val itemClick: OnItemClickListener) : RecyclerView.Adapter<GamesAdapter.GameHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.bindGame(games[position])
    }

    override fun getItemCount(): Int = games.size

    class GameHolder(view: View, private val itemClick: OnItemClickListener) : RecyclerView.ViewHolder(view) {
        private val pictureView = view.findViewById<ImageView>(R.id.imageViewPicture)
        private val titleView = view.findViewById<TextView>(R.id.textViewTitle)
        private val infoView = view.findViewById<TextView>(R.id.textViewInfo)

        fun bindGame(game: Game) {
            with(game) {
                Picasso.with(itemView.context).load(picture).into(pictureView)
                titleView.text = name
                infoView.text = "$releaseYear, $publisher"
                itemView.setOnClickListener{itemClick(this)}
            }
        }
    }
}

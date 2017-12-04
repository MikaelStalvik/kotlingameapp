package com.imploded.kotlingameapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.viewmodels.DetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_game_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class GameDetailActivity : AppCompatActivity() {

    private val viewModel = DetailViewModel()
    companion object {
        val ID = "GameDetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        val id = intent.getStringExtra("id")
        doAsync {
            val game = viewModel.getGame(id)
            uiThread {
                textViewDetailTitle.text = game.name
                Picasso.with(parent).load(game.picture).into(imageViewLarge)
                textViewPublisher.text = "Publisher: ${game.publisher}"
                textViewReleaseYear.text = "Released: ${game.releaseYear}"
                textViewPlatform.text = "Platform: ${game.platform}"
                textViewDescription.text = game.description
            }
        }
    }
}

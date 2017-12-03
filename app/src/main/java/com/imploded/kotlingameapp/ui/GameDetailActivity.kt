package com.imploded.kotlingameapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.imploded.kotlingameapp.R

class GameDetailActivity : AppCompatActivity() {

    companion object {
        val ID = "GameDetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
    }
}

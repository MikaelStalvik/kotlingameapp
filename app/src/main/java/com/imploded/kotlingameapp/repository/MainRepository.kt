package com.imploded.kotlingameapp.repository

import com.google.gson.Gson
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.fromJson
import java.net.URL

class MainRepository {

    fun getGames() : List<Game> {
        val url = "http://kotlinserver.azurewebsites.net/list"
        val json = URL(url).readText()
        return Gson().fromJson<List<Game>>(json).sortedBy { game -> game.name }
    }

}
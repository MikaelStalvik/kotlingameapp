package com.imploded.kotlingameapp.repository

import com.google.gson.Gson
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.fromJson
import java.net.URL

class DetailRepository {

    companion object {
        fun getGame(id: String) : Game {
            val url = "http://kotlinserver.azurewebsites.net/$id"
            val json = URL(url).readText()
            return Gson().fromJson<Game>(json)
        }
    }

}
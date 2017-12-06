package com.imploded.kotlingameapp.repository

import com.google.gson.Gson
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.fromJson
import java.net.URL

/**
 * Created by l19548726 on 2017-12-04.
 */
class DetailRepository {

    companion object {
        fun getGame(id: String) : Game {
            val url = "http://kotlinserver.azurewebsites.net/$id"
            val json = URL(url).readText()
            return Gson().fromJson<Game>(json)
        }
    }
    /*
    fun getGame(id: String) : Game {
        val url = "http://kotlinserver.azurewebsites.net/$id"
        val json = URL(url).readText()
        return Gson().fromJson<Game>(json)
    }*/
}
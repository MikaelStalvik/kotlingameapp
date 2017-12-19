package com.imploded.kotlingameapp.repository

import com.google.gson.Gson
import com.imploded.kotlingameapp.interfaces.MainRepositoryInterface
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.utils.AppConstants
import com.imploded.kotlingameapp.utils.fromJson
import java.net.URL

class MainRepository : MainRepositoryInterface {

    override fun getGames() : List<Game> {
        val url = AppConstants.GetGamesUrl
        val json = URL(url).readText()
        return Gson().fromJson<List<Game>>(json).sortedBy { game -> game.name }
    }

}
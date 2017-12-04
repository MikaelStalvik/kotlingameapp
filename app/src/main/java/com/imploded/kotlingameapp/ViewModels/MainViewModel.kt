package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.MainRepository

/**
 * Created by Mikael on 2017-12-03.
 */
class MainViewModel {
    fun getGames() : List<Game> {
        return MainRepository().getGames()
    }
}
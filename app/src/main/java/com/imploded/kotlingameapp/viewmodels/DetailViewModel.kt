package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.DetailRepository

/**
 * Created by l19548726 on 2017-12-04.
 */
class DetailViewModel {

    lateinit var game: Game

    fun getGameFromServer(id: String) {
        game =DetailRepository.getGame(id)
        //return DetailRepository().getGame(id)
    }
}
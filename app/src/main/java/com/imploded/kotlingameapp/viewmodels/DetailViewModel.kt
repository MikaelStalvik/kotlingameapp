package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.DetailRepository


class DetailViewModel {

    lateinit var game: Game

    fun getGameFromServer(id: String) {
        game = DetailRepository.getGame(id)
    }
}
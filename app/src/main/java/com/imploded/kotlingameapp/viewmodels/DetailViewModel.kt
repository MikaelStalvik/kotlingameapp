package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.DetailRepository

/**
 * Created by l19548726 on 2017-12-04.
 */
class DetailViewModel {
    fun getGame(id: String): Game {
        return DetailRepository().getGame(id)
    }
}
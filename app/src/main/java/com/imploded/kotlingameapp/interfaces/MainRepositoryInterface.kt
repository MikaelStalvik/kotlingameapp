package com.imploded.kotlingameapp.interfaces

import com.imploded.kotlingameapp.model.Game

interface MainRepositoryInterface {
    fun getGames() : List<Game>
}
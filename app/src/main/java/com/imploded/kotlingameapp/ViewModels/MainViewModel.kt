package com.imploded.kotlingameapp.ViewModels

import android.content.Intent
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.MainRepository
import com.imploded.kotlingameapp.ui.MainActivity

/**
 * Created by Mikael on 2017-12-03.
 */
class MainViewModel {
    fun getGames() : List<Game> {
        return MainRepository().getGames()
    }
}
package com.imploded.kotlingameapp.interfaces

import com.imploded.kotlingameapp.model.Game

/**
 * Created by Mikael on 2017-12-03.
 */
interface OnItemClickListener {
    operator fun invoke(game: Game)
}
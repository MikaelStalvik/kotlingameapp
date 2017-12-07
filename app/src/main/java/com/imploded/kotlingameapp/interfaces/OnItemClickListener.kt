package com.imploded.kotlingameapp.interfaces

import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.model.Game
import java.util.logging.Filter

/**
 * Created by Mikael on 2017-12-03.
 */
interface OnItemClickListener {
    operator fun invoke(game: Game)
}
interface OnItemCheckedListener {
    operator fun invoke(filter: FilterItem, checked: Boolean)
}
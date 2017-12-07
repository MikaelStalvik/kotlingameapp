package com.imploded.kotlingameapp.interfaces

import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.model.Game

interface OnItemClickListener {
    operator fun invoke(game: Game)
}
interface OnItemCheckedListener {
    operator fun invoke(filter: FilterItem, checked: Boolean)
}
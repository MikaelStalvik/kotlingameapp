package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.MainRepository
import com.imploded.kotlingameapp.ui.SortingActivity

/**
 * Created by Mikael on 2017-12-03.
 */
class MainViewModel {

    var activeSorting = SortingActivity.SortingNameId
    var ascending = true

    private val games: List<Game> by lazy {
        MainRepository().getGames()
    }

    fun getGamesForView(): List<Game> {
        return when (activeSorting) {
            SortingActivity.SortingNameId -> gamesSortedByName()
            SortingActivity.SortingPublisherId -> gamesSortedByPublisher()
            SortingActivity.SortingReleaseYearId -> gamesSortedByYear()
            else -> gamesSortedByName()
        }
    }

    private fun updateSorting(newSort: String) {
        val oldSorting = activeSorting
        activeSorting = newSort
        ascending = when (oldSorting) {
            activeSorting -> !ascending
            else -> true
        }
    }

    private fun gamesSortedByName(): List<Game> {
        updateSorting(SortingActivity.SortingNameId)
        if (ascending)
            return games.sortedBy { game -> game.name }
        return games.sortedByDescending { game -> game.name }
    }

    private fun gamesSortedByPublisher(): List<Game> {
        updateSorting(SortingActivity.SortingPublisherId)
        if (ascending)
            return games.sortedBy { game -> game.publisher }
        return games.sortedByDescending { game -> game.publisher }
    }

    private fun gamesSortedByYear(): List<Game> {
        updateSorting(SortingActivity.SortingReleaseYearId)
        if (ascending)
            return games.sortedBy { game -> game.releaseYear }
        return games.sortedByDescending { game -> game.releaseYear }
    }
}
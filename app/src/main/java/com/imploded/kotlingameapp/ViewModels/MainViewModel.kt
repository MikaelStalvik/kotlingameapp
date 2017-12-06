package com.imploded.kotlingameapp.viewmodels

import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.MainRepository
import com.imploded.kotlingameapp.ui.SortingActivity

/**
 * Created by Mikael on 2017-12-03.
 */
class MainViewModel {

    companion object {
        val SortedByName = "SortedByName"
        val SortedByPublisher = "SortedByPublisher"
        val SortedByReleaseYear = "SortedByReleaseYear"
    }

    var activeSorting = SortedByName
    var ascending = false

    private val games: List<Game> by lazy {
        MainRepository().getGames()
    }

    fun getGamesForView(): List<Game> {
        when (activeSorting) {
            SortedByName -> return gamesSortedByName()
            SortedByPublisher -> return gamesSortedByPublisher()
            SortedByReleaseYear -> return gamesSortedByYear()
            else -> return gamesSortedByName()
        }
    }

    fun translateSortingArgument(param: String): String {
        when (param) {
            SortingActivity.SortingNameId -> return SortedByName
            SortingActivity.SortingPublisherId -> return SortedByPublisher
            SortingActivity.SortingReleaseYearId -> return SortedByReleaseYear
            else -> return SortedByName
        }
    }

    fun gamesSortedByName(): List<Game> {
        val oldSorting = activeSorting
        activeSorting = SortedByName
        if (oldSorting == activeSorting) {
            ascending = !ascending
        }
        else {
            ascending = true
        }
        if (ascending)
            return games.sortedBy { game -> game.name }
        return games.sortedByDescending { game -> game.name }
    }

    fun gamesSortedByPublisher(): List<Game> {
        val oldSorting = activeSorting
        activeSorting = SortedByPublisher
        if (oldSorting == activeSorting) {
            ascending = !ascending
        }
        else {
            ascending = true
        }
        if (ascending)
            return games.sortedBy { game -> game.publisher }
        return games.sortedByDescending { game -> game.publisher }
    }

    fun gamesSortedByYear(): List<Game> {
        val oldSorting = activeSorting
        activeSorting = SortedByReleaseYear
        if (oldSorting == activeSorting) {
            ascending = !ascending
        }
        else {
            ascending = true
        }
        if (ascending)
            return games.sortedBy { game -> game.releaseYear }
        return games.sortedByDescending { game -> game.releaseYear }
    }
}
package com.imploded.kotlingameapp.viewmodels

import com.google.gson.Gson
import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.repository.MainRepository
import com.imploded.kotlingameapp.ui.SortingActivity
import com.imploded.kotlingameapp.utils.fromJson

/**
 * Created by Mikael on 2017-12-03.
 */
class MainViewModel {

    var activeSorting = SortingActivity.SortingNameId
    private var ascending = true

    private val allGames: List<Game> by lazy {
        MainRepository().getGames()
    }
    private var activeGames: List<Game> = listOf()

    var activeFilter = ""

    fun getAllPlatforms(): Map<String, Int> {
        val list = mutableListOf<String>()

        allGames.forEach({
            list.addAll(it.platforms)
        })

        return list.groupingBy { it }.eachCount()
    }

    fun getGamesForView(): List<Game> {
        updateFilter(activeFilter)
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
            return activeGames.sortedBy { game -> game.name }
        return activeGames.sortedByDescending { game -> game.name }
    }

    private fun gamesSortedByPublisher(): List<Game> {
        updateSorting(SortingActivity.SortingPublisherId)
        if (ascending)
            return activeGames.sortedBy { game -> game.publisher }
        return activeGames.sortedByDescending { game -> game.publisher }
    }

    private fun gamesSortedByYear(): List<Game> {
        updateSorting(SortingActivity.SortingReleaseYearId)
        if (ascending)
            return activeGames.sortedBy { game -> game.releaseYear }
        return activeGames.sortedByDescending { game -> game.releaseYear }
    }

    private fun updateFilter(json: String) {
        if (json.isNullOrEmpty()) {
            activeGames = allGames
            return
        }
        activeFilter = json
        val activeFilter = Gson().fromJson<List<FilterItem>>(json)

        val g = mutableListOf<Game>()
        val ps = activeFilter.filter { p -> p.checked }.map { p -> p.name }

        allGames.forEach{
            val game = it
            it.platforms.filter { ps.contains(it) }.map {
                if (!g.contains(game)) {
                    g.add(game)
                }
            }
        }
        activeGames = g // g.distinctBy { g -> g.id }

        /*
        var g = mutableListOf<Game>()
        val ps = listOf("Amiga", "PC")
        val pp = games.forEach{ game ->
            game.platforms.forEach{
                if (ps.contains(it)) g.add(game)
            }
        }
        */
        val k = 123
    }
}
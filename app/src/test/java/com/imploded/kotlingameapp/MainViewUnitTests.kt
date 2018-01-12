package com.imploded.kotlingameapp

import com.google.gson.Gson
import com.imploded.kotlingameapp.interfaces.MainRepositoryInterface
import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.model.Game
import com.imploded.kotlingameapp.viewmodels.MainViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MainViewUnitTests {

    private lateinit var viewModel: MainViewModel
    private lateinit var repo: MainRepositoryInterface

    @Before
    fun init() {
        repo = mock(MainRepositoryInterface::class.java)
        `when`(repo.getGames()).thenReturn(listOf(
                Game("0", "", "A", "", 1990, "", listOf("A")),
                Game("1", "", "B", "", 1991, "", listOf("B")),
                Game("2", "", "C", "", 1992, "", listOf("C")),
                Game("3", "", "D", "", 1993, "", listOf("A", "D"))
        ))
        viewModel = MainViewModel(repo)
    }

    @Test
    fun `When getting all games for view returned number of games shall be four`() {
        val games = viewModel.getGamesForView()
        assertEquals(4, games.count())
    }

    @Test
    fun `When applying filter to view then returned number of games shall be two`() {
        val filter = listOf(FilterItem("A", true), FilterItem("D", true))
        val filterJson = Gson().toJson(filter)
        viewModel.activeFilter = filterJson
        val games = viewModel.getGamesForView()
        assertEquals(2, games.count())
    }

}
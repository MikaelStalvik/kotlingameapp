package com.imploded.kotlingameapp

import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.viewmodels.FilterViewModel
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class FilterUnitTests {
    lateinit var viewModel: FilterViewModel

    @Before
    fun init() {
        viewModel = FilterViewModel()
        viewModel.filterItems = listOf(FilterItem("A", false), FilterItem("B", false), FilterItem("C", false))
    }

    @Test
    fun `When in filter view and selecting all items ensure that all items are checked`() {
        // ARRANGE
        // ACT
        viewModel.selectAll()
        // ASSERT
        assertTrue(viewModel.filterItems.filter { p -> p.checked }.map { p -> p.checked }.count() == viewModel.filterItems.count())
    }

    @Test
    fun `When in filter view and selecting no items ensure that no items are checked`() {
        // ARRANGE
        viewModel.selectAll()
        // ACT
        viewModel.selectNone()
        // ASSERT
        assertTrue(viewModel.filterItems.filter { p -> !p.checked }.map { p -> p.checked }.count() == viewModel.filterItems.count())
    }
}
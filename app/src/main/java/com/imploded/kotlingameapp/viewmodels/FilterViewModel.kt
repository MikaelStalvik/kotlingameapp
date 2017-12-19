package com.imploded.kotlingameapp.viewmodels

import com.google.gson.Gson
import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.utils.fromJson

class FilterViewModel {

    lateinit var filterItems: List<FilterItem>

    fun restoreActiveFilter(allPlatforms: String, activeFilter: String) {
        val platforms = Gson().fromJson<Map<String, Int>>(allPlatforms)
        val items = mutableListOf<FilterItem>()

        val activeFilters = getActiveFilters(activeFilter, platforms.map { p -> p.key })
        for ((platform, count) in platforms) {
            items.add(FilterItem(platform, activeFilters.contains(platform), count))
        }
        filterItems = items.sortedBy { p -> p.name }
    }

    fun selectAll() {
        for (item in filterItems) item.checked = true
    }

    fun selectNone() {
        for (item in filterItems) item.checked = false
    }

    fun getFilterItemsAsJson(): String {
        return Gson().toJson(filterItems)
    }

    private fun getActiveFilters(activeFilter: String, allPlatforms: List<String>): List<String> {
        if (activeFilter.isEmpty()) {
            return allPlatforms
        }
        return Gson().fromJson<List<FilterItem>>(activeFilter).filter { f -> f.checked }.map { f -> f.name }
    }

}
package com.imploded.kotlingameapp.viewmodels

import com.google.gson.Gson
import com.imploded.kotlingameapp.model.FilterItem
import com.imploded.kotlingameapp.utils.fromJson

/**
 * Created by l19548726 on 2017-12-07.
 */
class FilterViewModel {

    lateinit var filterItems: List<FilterItem>

    private fun getActiveFilters(filters: String, allPlatforms: List<String>): List<String> {
        if (filters.isEmpty()) {
            return allPlatforms
        }
        return Gson().fromJson<List<FilterItem>>(filters).filter { f -> f.checked }.map { f -> f.name }
    }
    fun restoreActiveFilter(allPlatforms: String, activeFilter: String) {
        val platforms = Gson().fromJson<Map<String, Int>>(allPlatforms)
        val items = mutableListOf<FilterItem>()

        val activeFilters = getActiveFilters(activeFilter, platforms.map { p -> p.key })
        for((platform, count) in platforms) {
            items.add(FilterItem(platform, activeFilters.contains(platform), count))
        }
        filterItems = items.sortedBy { p -> p.name }
    }

    fun getFilterItemsAsJson(): String {
        return Gson().toJson(filterItems)
    }

}